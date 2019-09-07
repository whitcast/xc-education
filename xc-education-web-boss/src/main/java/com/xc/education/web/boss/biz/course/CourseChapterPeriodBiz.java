package com.xc.education.web.boss.biz.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.course.common.bean.qo.CourseChapterPeriodQO;
import com.xc.education.course.common.bean.vo.CourseChapterPeriodVO;
import com.xc.education.course.feign.IBossCourseChapterPeriod;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;

/**
 * 课时信息 
 *
 * @author wujing
 */
@Component
public class CourseChapterPeriodBiz extends BaseBiz{

	@Autowired
	private IBossCourseChapterPeriod bossCourseChapterPeriod;

	public Page<CourseChapterPeriodVO> listForPage(CourseChapterPeriodQO qo) {
        return bossCourseChapterPeriod.listForPage(qo);
	}

	public int save(CourseChapterPeriodQO qo) {
		return bossCourseChapterPeriod.save(qo);
	}

	public int deleteById(Long id) {
		return bossCourseChapterPeriod.deleteById(id);
	}

	public CourseChapterPeriodVO getById(Long id) {
		return bossCourseChapterPeriod.getById(id);
	}

	public int updateById(CourseChapterPeriodQO qo) {
		return bossCourseChapterPeriod.updateById(qo);
	}
	
}
