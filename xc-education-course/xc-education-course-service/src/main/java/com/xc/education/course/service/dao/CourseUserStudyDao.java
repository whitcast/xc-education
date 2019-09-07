package com.xc.education.course.service.dao;

import com.xc.education.course.service.dao.impl.mapper.entity.CourseUserStudy;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseUserStudyExample;
import com.xc.education.util.base.Page;

public interface CourseUserStudyDao {
    int save(CourseUserStudy record);

    int deleteById(Long id);

    int updateById(CourseUserStudy record);

    CourseUserStudy getById(Long id);

    Page<CourseUserStudy> listForPage(int pageCurrent, int pageSize, CourseUserStudyExample example);

    /**
     * 根据用户编号、课程编号查找課程用户关联表
     * 
     * @param userNo
     * @param courseNo
     * @return
     */
	CourseUserStudy getByUserNoAndCourseId(Long userNo, Long courseId);
}