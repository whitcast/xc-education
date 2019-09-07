package com.xc.education.web.boss.biz.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.course.common.bean.qo.CourseChapterAuditQO;
import com.xc.education.course.common.bean.vo.CourseChapterAuditVO;
import com.xc.education.course.feign.IBossCourseChapterAudit;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;

/**
 * 章节信息-审核 
 *
 * @author wujing
 */
@Component
public class CourseChapterAuditBiz extends BaseBiz{

	@Autowired
	private IBossCourseChapterAudit bossCourseChapterAudit;

	public Page<CourseChapterAuditVO> listForPage(CourseChapterAuditQO qo) {
        return bossCourseChapterAudit.listForPage(qo);
	}

	public int save(CourseChapterAuditQO qo) {
		return bossCourseChapterAudit.save(qo);
	}

	public int deleteById(Long id) {
		return bossCourseChapterAudit.deleteById(id);
	}

	public CourseChapterAuditVO getById(Long id) {
		return bossCourseChapterAudit.getById(id);
	}

	public int updateById(CourseChapterAuditQO qo) {
		return bossCourseChapterAudit.updateById(qo);
	}
	
}
