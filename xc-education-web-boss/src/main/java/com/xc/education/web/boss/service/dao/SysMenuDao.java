/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.xc.education.web.boss.service.dao;

import java.util.List;

import com.xc.education.util.base.Page;
import com.xc.education.web.boss.service.dao.impl.mapper.entity.SysMenu;
import com.xc.education.web.boss.service.dao.impl.mapper.entity.SysMenuExample;

public interface SysMenuDao {
	Long save(SysMenu record);

	int deleteById(Long id);

	int updateById(SysMenu record);

	SysMenu getById(Long id);

	Page<SysMenu> listForPage(int pageCurrent, int pageSize, SysMenuExample example);

	List<SysMenu> listByParentId(Long parentId);

	List<SysMenu> listAll();

}
