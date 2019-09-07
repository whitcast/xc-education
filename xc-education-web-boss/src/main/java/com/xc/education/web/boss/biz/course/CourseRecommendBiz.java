package com.xc.education.web.boss.biz.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.course.common.bean.qo.CourseQO;
import com.xc.education.course.common.bean.qo.CourseRecommendQO;
import com.xc.education.course.common.bean.vo.CourseRecommendVO;
import com.xc.education.course.common.bean.vo.CourseVO;
import com.xc.education.course.feign.IBossCourse;
import com.xc.education.course.feign.IBossCourseRecommend;
import com.xc.education.util.base.Page;
import com.xc.education.web.boss.common.BizBoss;

/**
 * 课程推荐
 *
 * @author wujing
 */
@Component
public class CourseRecommendBiz extends BizBoss {

	@Autowired
	private IBossCourseRecommend bossCourseRecommend;
	@Autowired
	private IBossCourse bossCourse;

	public Page<CourseRecommendVO> listForPage(CourseRecommendQO qo) {
		return bossCourseRecommend.listForPage(qo);
	}

	public int save(CourseRecommendQO qo) {
		return bossCourseRecommend.save(qo);
	}

	public int deleteById(Long id) {
		return bossCourseRecommend.deleteById(id);
	}

	public CourseRecommendVO getById(Long id) {
		return bossCourseRecommend.getById(id);
	}

	public int updateById(CourseRecommendQO qo) {
		return bossCourseRecommend.updateById(qo);
	}

	public int updateByStatusId(CourseRecommendQO qo) {
		return bossCourseRecommend.updateById(qo);
	}

	public Page<CourseVO> listForPage(CourseQO qo) {
		return bossCourse.listForPage(qo);
	}

}
