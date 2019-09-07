package com.xc.education.user.service.dao.impl;

import com.xc.education.user.service.dao.UserLogLoginDao;
import com.xc.education.user.service.dao.impl.mapper.UserLogLoginMapper;
import com.xc.education.user.service.dao.impl.mapper.entity.UserLogLogin;
import com.xc.education.user.service.dao.impl.mapper.entity.UserLogLoginExample;
import com.xc.education.util.base.Page;
import com.xc.education.util.base.PageUtil;
import com.xc.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserLogLoginDaoImpl implements UserLogLoginDao {
    @Autowired
    private UserLogLoginMapper userLogLoginMapper;

    public int save(UserLogLogin record) {
        record.setId(IdWorker.getId());
        return this.userLogLoginMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.userLogLoginMapper.deleteByPrimaryKey(id);
    }

    public int updateById(UserLogLogin record) {
        return this.userLogLoginMapper.updateByPrimaryKeySelective(record);
    }

    public UserLogLogin getById(Long id) {
        return this.userLogLoginMapper.selectByPrimaryKey(id);
    }

    public Page<UserLogLogin> listForPage(int pageCurrent, int pageSize, UserLogLoginExample example) {
        int count = this.userLogLoginMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<UserLogLogin>(count, totalPage, pageCurrent, pageSize, this.userLogLoginMapper.selectByExample(example));
    }
}