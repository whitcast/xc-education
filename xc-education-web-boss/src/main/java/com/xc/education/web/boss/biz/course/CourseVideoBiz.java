package com.xc.education.web.boss.biz.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.course.common.bean.qo.CourseVideoQO;
import com.xc.education.course.common.bean.vo.CourseVideoVO;
import com.xc.education.course.feign.IBossCourseVideo;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;

/**
 * 课程视频信息 
 *
 * @author wuyun
 */
@Component
public class CourseVideoBiz extends BaseBiz{

	@Autowired
	private IBossCourseVideo bossCourseVideo;

	public Page<CourseVideoVO> listForPage(CourseVideoQO qo) {
        return bossCourseVideo.listForPage(qo);
	}

	public int save(CourseVideoQO qo) {
		return bossCourseVideo.save(qo);
	}

	public int deleteById(Long id) {
		return bossCourseVideo.deleteById(id);
	}

	public CourseVideoVO getById(Long id) {
		return bossCourseVideo.getById(id);
	}

	public int updateById(CourseVideoQO qo) {
		return bossCourseVideo.updateById(qo);
	}
	
}
