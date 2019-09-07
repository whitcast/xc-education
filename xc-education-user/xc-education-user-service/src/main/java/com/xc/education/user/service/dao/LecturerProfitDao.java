package com.xc.education.user.service.dao;

import com.xc.education.user.service.dao.impl.mapper.entity.LecturerProfit;
import com.xc.education.user.service.dao.impl.mapper.entity.LecturerProfitExample;
import com.xc.education.util.base.Page;

public interface LecturerProfitDao {
	int save(LecturerProfit record);

	int deleteById(Long id);

	int updateById(LecturerProfit record);

	LecturerProfit getById(Long id);

	Page<LecturerProfit> listForPage(int pageCurrent, int pageSize, LecturerProfitExample example);
}