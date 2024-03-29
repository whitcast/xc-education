/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.xc.education.course.service.biz;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.xc.education.course.service.dao.CourseVideoDao;
import com.xc.education.course.service.dao.FileStorageDao;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseVideo;
import com.xc.education.course.service.dao.impl.mapper.entity.FileStorage;
import com.xc.education.system.common.bean.vo.SysVO;
import com.xc.education.system.feign.IBossSys;
import com.xc.education.util.aliyun.Aliyun;
import com.xc.education.util.aliyun.AliyunUtil;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Result;
import com.xc.education.util.config.SystemUtil;
import com.xc.education.util.enums.FileStorageTypeEnum;
import com.xc.education.util.enums.FileTypeEnum;
import com.xc.education.util.enums.PlatformEnum;
import com.xc.education.util.enums.VideoStatusEnum;
import com.xc.education.util.polyv.PolyvUtil;
import com.xc.education.util.polyv.UploadFile;
import com.xc.education.util.polyv.UploadFileResult;
import com.xc.education.util.tools.BeanUtil;
import com.xc.education.util.tools.IdWorker;
import com.xc.education.util.tools.StrUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 上传接口
 * 
 * @author wuyun
 */
@Component
public class ApiUploadBiz extends BaseBiz {

	@Autowired
	private CourseVideoDao courseVideoDao;
	@Autowired
	private FileStorageDao fileStorageDao;

	@Autowired
	private IBossSys bossSys;

	/**
	 * 上传视频接口
	 * 
	 * @author wuyun
	 */
	public Result<String> uploadVideo(MultipartFile videoFile) {
		// 视频上传
		if (videoFile == null || videoFile.isEmpty()) {
			return Result.error("请选择视频进行上传");
		}

		// 获取上传文件的原名
		String fileName = videoFile.getOriginalFilename();
		boolean fileStatus = true;
		List<String> fileTypes = Arrays.asList("avi", "mp4", "flv", "mpg", "mov", "asf", "3gp", "f4v", "wmv", "x-ms-wmv\n");
		for (String filetype : fileTypes) {
			// 上传文件的原名+小写+后缀
			if (fileName.toLowerCase().endsWith(filetype)) {
				fileStatus = false;
				break;
			}
		}
		if (fileStatus) {
			return Result.error("上传的视频类型不正确");
		}

		Long videoNo = IdWorker.getId(); // 当作存储到本地的文件名，方便定时任务的处理

		// 1、上传到本地
		File targetFile = new File(SystemUtil.PERIOD_VIDEO_PATH + videoNo.toString() + "." + StrUtil.getSuffix(fileName));
		targetFile.setLastModified(System.currentTimeMillis());// 设置最后修改时间
		// 判断文件目录是否存在，不存在就创建文件目录
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}
		try {
			videoFile.transferTo(targetFile);
		} catch (Exception e) {
			logger.error("上传到本地失败", e);
			return Result.error("上传文件出错，请重新上传");
		}

		// 新增课程视频表信息
		CourseVideo courseVideo = new CourseVideo();
		courseVideo.setVideoNo(videoNo);
		courseVideo.setGmtCreate(null);
		courseVideo.setGmtModified(null);
		courseVideo.setVideoName(fileName);
		courseVideo.setVideoStatus(VideoStatusEnum.WAIT.getCode());
		int result = courseVideoDao.save(courseVideo);

		if (result > 0) {
			callbackExecutor.execute(new Runnable() {
				@Override
				public void run() {
					// 2、异步上传到保利威视
					UploadFile uploadFile = new UploadFile();
					uploadFile.setTitle(fileName);
					uploadFile.setDesc(fileName);
					uploadFile.setTag(videoFile.getOriginalFilename());
					uploadFile.setCataid(1L);

					// 获取系统配置信息
					SysVO sys = bossSys.getSys();

					UploadFileResult result = PolyvUtil.uploadFile(targetFile, uploadFile, sys.getPolyvWritetoken());
					if (result == null) {
						// 上传异常，不再进行处理，定时任务会继续进行处理
						return;
					}

					courseVideo.setVideoLength(result.getDuration());
					courseVideo.setVideoVid(result.getVid());
					courseVideo.setVideoStatus(VideoStatusEnum.SUCCES.getCode());
					courseVideoDao.updateById(courseVideo);

					// 3、异步上传到阿里云
					String videoOasId = AliyunUtil.uploadDoc(PlatformEnum.COURSE, targetFile, BeanUtil.copyProperties(sys, Aliyun.class));
					courseVideo.setVideoOasId(videoOasId);
					courseVideoDao.updateById(courseVideo);

					// 根据视频编号、课时ID查询课程视频信息
					CourseVideo courseVideo = courseVideoDao.getByVideoNoAndPeriodId(videoNo, Long.valueOf(0));

					// 根据视频编号更新视频信息
					List<CourseVideo> list = courseVideoDao.listByVideoNo(videoNo);
					for (CourseVideo video : list) {
						video.setVideoLength(courseVideo.getVideoLength());
						video.setVideoVid(courseVideo.getVideoVid());
						video.setVideoStatus(VideoStatusEnum.SUCCES.getCode());
						video.setVideoOasId(courseVideo.getVideoOasId());
						courseVideoDao.updateById(video);
					}

					// 4、成功删除本地文件
					if (targetFile.isFile() && targetFile.exists()) {
						targetFile.delete();
					}
				}
			});
		} else {
			return Result.error("系统异常，请重试");
		}
		return Result.success(String.valueOf(courseVideo.getVideoNo()));
	}

	/**
	 * 上传图片接口
	 * 
	 * @author wuyun
	 */
	public Result<String> uploadPic(MultipartFile picFile) {
		if (ObjectUtil.isNotNull(picFile)) {
			// 获取系统配置信息
			SysVO sys = bossSys.getSys();
			if (ObjectUtil.isNull(sys)) {
				Result.error("未配置系统配置表");
			}
			Long fileNo = IdWorker.getId();
			// 1、上传到本地
			if (sys.getFileType().equals(FileTypeEnum.LOCAL.getCode())) {
				File pic = new File(SystemUtil.PIC_STORAGE_PATH + fileNo.toString() + "." + StrUtil.getSuffix(picFile.getOriginalFilename()));
				try {
					// 判断文件目录是否存在，不存在就创建文件目录
					if (!pic.getParentFile().exists()) {
						pic.getParentFile().mkdirs();// 创建父级文件路径
					}
					picFile.transferTo(pic);
					FileStorage fileStorage = new FileStorage();
					fileStorage.setFileName(picFile.getOriginalFilename());
					fileStorage.setFileNo(fileNo);
					fileStorage.setFileSize(picFile.getSize());
					fileStorage.setFileType(FileStorageTypeEnum.PICTURE.getCode());
					fileStorage.setFileUrl(pic.toString());
					fileStorageDao.save(fileStorage);
					return Result.success(pic.toString());
				} catch (Exception e) {
					logger.error("上传到本地失败", e);
					return Result.error("上传文件出错，请重新上传");
				}
			}
			return Result.success(AliyunUtil.uploadPic(PlatformEnum.COURSE, picFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		return Result.error("请选择上传的图片");
	}

	/**
	 * 上传文档接口
	 * 
	 * @author wuyun
	 */
	public Result<String> uploadDoc(MultipartFile docFile) {
		if (ObjectUtil.isNotNull(docFile)) {
			// 获取系统配置信息
			SysVO sys = bossSys.getSys();
			if (ObjectUtil.isNull(sys)) {
				Result.error("未配置系统配置表");
			}
			Long fileNo = IdWorker.getId();
			// 1、上传到本地
			if (sys.getFileType().equals(FileTypeEnum.LOCAL.getCode())) {
				File pic = new File(SystemUtil.DOC_STORAGE_PATH + fileNo.toString() + "." + StrUtil.getSuffix(docFile.getOriginalFilename()));
				try {
					// 判断文件目录是否存在，不存在就创建文件目录
					if (!pic.getParentFile().exists()) {
						pic.getParentFile().mkdirs();// 创建父级文件路径
					}
					docFile.transferTo(pic);
					FileStorage fileStorage = new FileStorage();
					fileStorage.setFileName(docFile.getOriginalFilename());
					fileStorage.setFileNo(fileNo);
					fileStorage.setFileSize(docFile.getSize());
					fileStorage.setFileType(FileStorageTypeEnum.DOC.getCode());
					fileStorage.setFileUrl(pic.toString());
					fileStorageDao.save(fileStorage);
					return Result.success(pic.toString());
				} catch (Exception e) {
					logger.error("上传到本地失败", e);
					return Result.error("上传文件出错，请重新上传");
				}
			}
			return Result.success(AliyunUtil.uploadDoc(PlatformEnum.COURSE, docFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		return Result.error("请选择上传的文件");

	}

}
