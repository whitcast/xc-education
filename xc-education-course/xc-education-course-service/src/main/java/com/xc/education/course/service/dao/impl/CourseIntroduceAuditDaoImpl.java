package com.xc.education.course.service.dao.impl;

import com.xc.education.course.service.dao.CourseIntroduceAuditDao;
import com.xc.education.course.service.dao.impl.mapper.CourseIntroduceAuditMapper;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseIntroduceAudit;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseIntroduceAuditExample;
import com.xc.education.util.base.Page;
import com.xc.education.util.base.PageUtil;
import com.xc.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseIntroduceAuditDaoImpl implements CourseIntroduceAuditDao {
    @Autowired
    private CourseIntroduceAuditMapper courseIntroduceAuditMapper;

    public int save(CourseIntroduceAudit record) {
        record.setId(IdWorker.getId());
        return this.courseIntroduceAuditMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.courseIntroduceAuditMapper.deleteByPrimaryKey(id);
    }

    public int updateById(CourseIntroduceAudit record) {
    	record.setGmtCreate(null);
    	record.setGmtModified(null);
        return this.courseIntroduceAuditMapper.updateByPrimaryKeySelective(record);
    }

    public CourseIntroduceAudit getById(Long id) {
        return this.courseIntroduceAuditMapper.selectByPrimaryKey(id);
    }

    public Page<CourseIntroduceAudit> listForPage(int pageCurrent, int pageSize, CourseIntroduceAuditExample example) {
        int count = this.courseIntroduceAuditMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<CourseIntroduceAudit>(count, totalPage, pageCurrent, pageSize, this.courseIntroduceAuditMapper.selectByExample(example));
    }
}