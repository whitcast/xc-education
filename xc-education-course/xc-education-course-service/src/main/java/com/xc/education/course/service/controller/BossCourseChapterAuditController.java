package com.xc.education.course.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.xc.education.course.common.bean.qo.CourseChapterAuditQO;
import com.xc.education.course.common.bean.vo.CourseChapterAuditVO;
import com.xc.education.course.common.interfaces.BossCourseChapterAudit;
import com.xc.education.course.service.controller.biz.BossCourseChapterAuditBiz;
import com.xc.education.util.base.BaseController;
import com.xc.education.util.base.Page;

/**
 * 章节信息-审核 
 *
 * @author wujing
 */
@RestController
public class BossCourseChapterAuditController extends BaseController implements BossCourseChapterAudit{

	@Autowired
	private BossCourseChapterAuditBiz biz;
	
	@Override
	public Page<CourseChapterAuditVO> listForPage(@RequestBody CourseChapterAuditQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody CourseChapterAuditQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@PathVariable(value = "id") Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody CourseChapterAuditQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public CourseChapterAuditVO getById(@PathVariable(value = "id") Long id){
		return biz.getById(id);
	}
	
}
