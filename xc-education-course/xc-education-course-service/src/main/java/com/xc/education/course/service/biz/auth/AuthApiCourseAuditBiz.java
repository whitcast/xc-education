package com.xc.education.course.service.biz.auth;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.xc.education.course.service.common.bo.auth.AuthCourseAuditDeleteBO;
import com.xc.education.course.service.common.bo.auth.AuthCourseAuditListBO;
import com.xc.education.course.service.common.bo.auth.AuthCourseAuditSaveBO;
import com.xc.education.course.service.common.bo.auth.AuthCourseAuditStandBO;
import com.xc.education.course.service.common.bo.auth.AuthCourseAuditUpdateBO;
import com.xc.education.course.service.common.bo.auth.AuthCourseAuditViewBO;
import com.xc.education.course.service.common.dto.auth.AuthCourseAuditListDTO;
import com.xc.education.course.service.common.dto.auth.AuthCourseAuditSaveDTO;
import com.xc.education.course.service.common.dto.auth.AuthCourseAuditViewDTO;
import com.xc.education.course.service.dao.CourseAuditDao;
import com.xc.education.course.service.dao.CourseChapterAuditDao;
import com.xc.education.course.service.dao.CourseChapterPeriodAuditDao;
import com.xc.education.course.service.dao.CourseDao;
import com.xc.education.course.service.dao.CourseIntroduceAuditDao;
import com.xc.education.course.service.dao.impl.mapper.entity.Course;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseAuditExample;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseAuditExample.Criteria;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseIntroduceAudit;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;
import com.xc.education.util.base.PageUtil;
import com.xc.education.util.base.Result;
import com.xc.education.util.enums.AuditStatusEnum;
import com.xc.education.util.enums.IsFreeEnum;
import com.xc.education.util.enums.IsPutawayEnum;
import com.xc.education.util.enums.ResultEnum;
import com.xc.education.util.enums.StatusIdEnum;
import com.xc.education.util.tools.BeanUtil;
import com.xc.education.util.tools.Constants;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@Component
public class AuthApiCourseAuditBiz extends BaseBiz {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseAuditDao courseAuditDao;
	@Autowired
	private CourseChapterAuditDao courseChapterAduitDao;
	@Autowired
	private CourseChapterPeriodAuditDao courseChapterPeriodAuditDao;
	@Autowired
	private CourseIntroduceAuditDao courseIntroduceAuditDao;

	/**
	 * 讲师课程分页接口
	 * 
	 * @param authCourseAuditListBO
	 * @return
	 * @author wuyun
	 */
	public Result<Page<AuthCourseAuditListDTO>> listForPage(AuthCourseAuditListBO authCourseAuditListBO) {
		if (authCourseAuditListBO.getLecturerUserNo() == null) {
			return Result.error("lecturerUserNo不能为空");
		}
		CourseAuditExample example = new CourseAuditExample();
		Criteria c = example.createCriteria();
		c.andLecturerUserNoEqualTo(authCourseAuditListBO.getLecturerUserNo());
		c.andStatusIdLessThan(Constants.FREEZE);
		if (authCourseAuditListBO.getAuditStatus() != null) {
			c.andAuditStatusEqualTo(authCourseAuditListBO.getAuditStatus());
		}
		example.setOrderByClause("audit_status asc, sort desc, id desc");
		Page<CourseAudit> page = courseAuditDao.listForPage(authCourseAuditListBO.getPageCurrent(), authCourseAuditListBO.getPageSize(), example);
		Page<AuthCourseAuditListDTO> dtoPage = PageUtil.transform(page, AuthCourseAuditListDTO.class);
		for (AuthCourseAuditListDTO dto : dtoPage.getList()) {
			Course course = courseDao.getById(dto.getId());
			if (ObjectUtil.isNull(course)) {
				dto.setIsDelete(1);
			} else {
				dto.setIsDelete(0);
			}
		}
		return Result.success(dtoPage);
	}

	/**
	 * 讲师课程详情接口
	 * 
	 * @param id
	 * @return
	 * @author wuyun
	 */
	public Result<AuthCourseAuditViewDTO> view(AuthCourseAuditViewBO authCourseAuditViewBO) {
		if (StringUtils.isEmpty(authCourseAuditViewBO.getId())) {
			return Result.error("课程id不能为空");
		}

		// 查询课程信息
		CourseAudit courseAudit = courseAuditDao.getById(authCourseAuditViewBO.getId());
		if (ObjectUtil.isNull(courseAudit)) {
			return Result.error("没有找到该课程信息");
		}
		AuthCourseAuditViewDTO dto = BeanUtil.copyProperties(courseAudit, AuthCourseAuditViewDTO.class);
		// 课程介绍
		CourseIntroduceAudit courseIntroduceAudit = courseIntroduceAuditDao.getById(courseAudit.getIntroduceId());
		dto.setIntroduce(courseIntroduceAudit.getIntroduce());

		return Result.success(dto);
	}

	/**
	 * 讲师课程保存接口
	 * 
	 * @param authCourseAuditSaveBO
	 * @return
	 * @author wuyun
	 */
	@Transactional
	public Result<AuthCourseAuditSaveDTO> save(AuthCourseAuditSaveBO authCourseAuditSaveBO) {
		if (StringUtils.isEmpty(authCourseAuditSaveBO.getUserNo())) {
			return Result.error("userNo不能为空");
		}
		// 原价小于0
		if (authCourseAuditSaveBO.getCourseOriginal().compareTo(BigDecimal.valueOf(0)) == -1) {
			return Result.error("售价不能小于0");
		}
		// 课程收费但价格为空
		if (IsFreeEnum.CHARGE.getCode().equals(authCourseAuditSaveBO.getIsFree())) {
			if (authCourseAuditSaveBO.getCourseOriginal() == null) {
				return Result.error("价格不能为空");
			}
		}

		// 课程介绍
		CourseIntroduceAudit courseIntroduceAudit = new CourseIntroduceAudit();
		courseIntroduceAudit.setIntroduce(authCourseAuditSaveBO.getIntroduce());
		courseIntroduceAuditDao.save(courseIntroduceAudit);

		// 课程
		CourseAudit record = BeanUtil.copyProperties(authCourseAuditSaveBO, CourseAudit.class);
		if (IsFreeEnum.FREE.getCode().equals(authCourseAuditSaveBO.getIsFree())) {
			// 课程免费就设置价格为0(原价、优惠价)
			record.setCourseOriginal(BigDecimal.valueOf(0));
			record.setCourseDiscount(BigDecimal.valueOf(0));
		}
		record.setStatusId(StatusIdEnum.YES.getCode());
		record.setIsPutaway(IsPutawayEnum.YES.getCode());
		record.setAuditStatus(AuditStatusEnum.WAIT.getCode());
		record.setIntroduceId(courseIntroduceAudit.getId());
		record.setCourseDiscount(authCourseAuditSaveBO.getCourseOriginal());
		record.setLecturerUserNo(authCourseAuditSaveBO.getUserNo());
		// 查询更新后的课程审核信息
		if (courseAuditDao.save(record) > 0) {
			return Result.success(BeanUtil.copyProperties(record, AuthCourseAuditSaveDTO.class));
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	/**
	 * 讲师课程更新接口
	 * 
	 * @param authCourseAuditUpdateBO
	 * @return
	 * @author wuyun
	 */
	@Transactional
	public Result<Integer> update(AuthCourseAuditUpdateBO authCourseAuditUpdateBO) {
		if (StringUtils.isEmpty(authCourseAuditUpdateBO.getUserNo())) {
			return Result.error("userNo不能为空");
		}
		// 课程收费但价格为空
		if (IsFreeEnum.CHARGE.getCode().equals(authCourseAuditUpdateBO.getIsFree())) {
			if (authCourseAuditUpdateBO.getCourseOriginal() == null) {
				return Result.error("价格不能为空");
			}
		}
		// 原价小于0
		if (authCourseAuditUpdateBO.getCourseOriginal().compareTo(BigDecimal.valueOf(0)) == -1) {
			return Result.error("售价不能小于0");
		}

		CourseAudit courseAudit = courseAuditDao.getById(authCourseAuditUpdateBO.getId());
		if (courseAudit == null) {
			return Result.error("id不正确");
		}
		if (!authCourseAuditUpdateBO.getUserNo().equals(courseAudit.getLecturerUserNo())) {
			return Result.error("传入的useNo与该课程的讲师useNo不一致");
		}

		// 课程简介更新
		CourseIntroduceAudit courseIntroduceAudit = new CourseIntroduceAudit();
		courseIntroduceAudit.setId(courseAudit.getIntroduceId());
		courseIntroduceAudit.setIntroduce(authCourseAuditUpdateBO.getIntroduce());
		courseIntroduceAuditDao.updateById(courseIntroduceAudit);

		// 课程更新
		CourseAudit record = BeanUtil.copyProperties(authCourseAuditUpdateBO, CourseAudit.class);
		if (IsFreeEnum.FREE.getCode().equals(authCourseAuditUpdateBO.getIsFree())) {
			// 课程免费就设置价格为0(原价、优惠价)
			record.setCourseOriginal(BigDecimal.valueOf(0));
			record.setCourseDiscount(BigDecimal.valueOf(0));
		}
		if (authCourseAuditUpdateBO.getCategoryId1() == null) {
			record.setCategoryId1(0L);
		}
		if (authCourseAuditUpdateBO.getCategoryId2() == null) {
			record.setCategoryId2(0L);
		}
		if (authCourseAuditUpdateBO.getCategoryId3() == null) {
			record.setCategoryId3(0L);
		}
		record.setAuditStatus(AuditStatusEnum.WAIT.getCode());
		record.setCourseDiscount(authCourseAuditUpdateBO.getCourseOriginal());
		record.setAuditOpinion("");

		if (courseAuditDao.updateById(record) > 0) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
	}

	/**
	 * 讲师课程上下架接口
	 * 
	 * @param authCourseAuditStandBO
	 * @return
	 * @author wuyun
	 */
	public Result<Integer> stand(AuthCourseAuditStandBO authCourseAuditStandBO) {
		if (StringUtils.isEmpty(authCourseAuditStandBO.getId())) {
			return Result.error("课程ID不能为空");
		}
		if (StringUtils.isEmpty(authCourseAuditStandBO.getUserNo())) {
			return Result.error("userNo不能为空");
		}
		CourseAudit courseAudit = courseAuditDao.getById(authCourseAuditStandBO.getId());
		if (courseAudit == null) {
			return Result.error("id不正确");
		}
		if (!authCourseAuditStandBO.getUserNo().equals(courseAudit.getLecturerUserNo())) {
			return Result.error("传入的useNo与该课程的讲师useNo不一致");
		}
		CourseAudit record = BeanUtil.copyProperties(authCourseAuditStandBO, CourseAudit.class);
		record.setAuditStatus(AuditStatusEnum.WAIT.getCode());
		int result = courseAuditDao.updateById(record);
		if (result > 0) {
			return Result.success(result);
		}
		return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
	}

	/**
	 * 讲师课程删除接口
	 * 
	 * @param id
	 * @return
	 * @author wuyun
	 */
	@Transactional
	public Result<Integer> delete(AuthCourseAuditDeleteBO authCourseAuditDeleteBO) {
		if (authCourseAuditDeleteBO.getId() == null) {
			return Result.error("id不能为空");
		}
		if (StringUtils.isEmpty(authCourseAuditDeleteBO.getUserNo())) {
			return Result.error("userNo不能为空");
		}
		CourseAudit courseAudit = courseAuditDao.getById(authCourseAuditDeleteBO.getId());
		if (ObjectUtil.isNull(courseAudit)) {
			return Result.error("找不到课程信息");
		}
		if (!authCourseAuditDeleteBO.getUserNo().equals(courseAudit.getLecturerUserNo())) {
			return Result.error("传入的useNo与该课程的讲师useNo不一致");
		}
		// 查找课程，存在则不删除
		Course course = courseDao.getByCourseIdAndStatusId(courseAudit.getId(), StatusIdEnum.YES.getCode());
		if (ObjectUtil.isNotNull(course)) {
			return Result.error("该课程已审核，无法删除");
		}

		// 删除章节
		courseChapterAduitDao.deleteByCourseId(courseAudit.getId());

		// 删除课时
		courseChapterPeriodAuditDao.deleteByCourseId(courseAudit.getId());

		return Result.success(courseAuditDao.deleteById(courseAudit.getId()));
	}

}
