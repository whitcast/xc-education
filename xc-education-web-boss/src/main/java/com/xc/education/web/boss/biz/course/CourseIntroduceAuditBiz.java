package com.xc.education.web.boss.biz.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.course.common.bean.qo.CourseIntroduceAuditQO;
import com.xc.education.course.common.bean.vo.CourseIntroduceAuditVO;
import com.xc.education.course.feign.IBossCourseIntroduceAudit;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;

/**
 * 课程介绍信息 
 *
 * @author wujing
 */
@Component
public class CourseIntroduceAuditBiz extends BaseBiz{

	@Autowired
	private IBossCourseIntroduceAudit bossCourseIntroduceAudit;

	public Page<CourseIntroduceAuditVO> listForPage(CourseIntroduceAuditQO qo) {
        return bossCourseIntroduceAudit.listForPage(qo);
	}

	public int save(CourseIntroduceAuditQO qo) {
		return bossCourseIntroduceAudit.save(qo);
	}

	public int deleteById(Long id) {
		return bossCourseIntroduceAudit.deleteById(id);
	}

	public CourseIntroduceAuditVO getById(Long id) {
		return bossCourseIntroduceAudit.getById(id);
	}

	public int updateById(CourseIntroduceAuditQO qo) {
		return bossCourseIntroduceAudit.updateById(qo);
	}
	
}
