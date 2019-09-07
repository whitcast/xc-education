package com.xc.education.course.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.course.common.bean.qo.CourseChapterQO;
import com.xc.education.course.common.bean.vo.CourseChapterVO;
import com.xc.education.course.service.dao.CourseChapterDao;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseChapter;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseChapterExample;
import com.xc.education.util.base.Page;
import com.xc.education.util.base.PageUtil;
import com.xc.education.util.tools.BeanUtil;

/**
 * 章节信息 
 *
 * @author wujing
 */
@Component
public class BossCourseChapterBiz {

	@Autowired
	private CourseChapterDao dao;

	public Page<CourseChapterVO> listForPage(CourseChapterQO qo) {
	    CourseChapterExample example = new CourseChapterExample();
	    example.setOrderByClause(" id desc ");
        Page<CourseChapter> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseChapterVO.class);
	}

	public int save(CourseChapterQO qo) {
	    CourseChapter record = BeanUtil.copyProperties(qo, CourseChapter.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public CourseChapterVO getById(Long id) {
	    CourseChapter record = dao.getById(id);
		return BeanUtil.copyProperties(record, CourseChapterVO.class);
	}

	public int updateById(CourseChapterQO qo) {
	    CourseChapter record = BeanUtil.copyProperties(qo, CourseChapter.class);
		return dao.updateById(record);
	}
	
}
