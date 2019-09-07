package com.xc.education.user.service.dao;

import com.xc.education.user.service.dao.impl.mapper.entity.UserLogModified;
import com.xc.education.user.service.dao.impl.mapper.entity.UserLogModifiedExample;
import com.xc.education.util.base.Page;

public interface UserLogModifiedDao {
    int save(UserLogModified record);

    int deleteById(Long id);

    int updateById(UserLogModified record);

    UserLogModified getById(Long id);

    Page<UserLogModified> listForPage(int pageCurrent, int pageSize, UserLogModifiedExample example);
}