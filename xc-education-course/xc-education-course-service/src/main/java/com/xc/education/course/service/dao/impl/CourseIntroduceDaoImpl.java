package com.xc.education.course.service.dao.impl;

import com.xc.education.course.service.dao.CourseIntroduceDao;
import com.xc.education.course.service.dao.impl.mapper.CourseIntroduceMapper;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseIntroduce;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseIntroduceExample;
import com.xc.education.util.base.Page;
import com.xc.education.util.base.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseIntroduceDaoImpl implements CourseIntroduceDao {
    @Autowired
    private CourseIntroduceMapper courseIntroduceMapper;

    public int save(CourseIntroduce record) {
        return this.courseIntroduceMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.courseIntroduceMapper.deleteByPrimaryKey(id);
    }

    public int updateById(CourseIntroduce record) {
        return this.courseIntroduceMapper.updateByPrimaryKeySelective(record);
    }

    public CourseIntroduce getById(Long id) {
        return this.courseIntroduceMapper.selectByPrimaryKey(id);
    }

    public Page<CourseIntroduce> listForPage(int pageCurrent, int pageSize, CourseIntroduceExample example) {
        int count = this.courseIntroduceMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<CourseIntroduce>(count, totalPage, pageCurrent, pageSize, this.courseIntroduceMapper.selectByExample(example));
    }
}