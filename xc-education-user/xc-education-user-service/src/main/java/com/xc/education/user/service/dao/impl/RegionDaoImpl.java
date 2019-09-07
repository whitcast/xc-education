package com.xc.education.user.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xc.education.user.service.dao.RegionDao;
import com.xc.education.user.service.dao.impl.mapper.RegionMapper;
import com.xc.education.user.service.dao.impl.mapper.entity.Region;
import com.xc.education.user.service.dao.impl.mapper.entity.RegionExample;
import com.xc.education.util.base.Page;
import com.xc.education.util.base.PageUtil;
import com.xc.education.util.tools.IdWorker;

@Repository
public class RegionDaoImpl implements RegionDao {
	@Autowired
	private RegionMapper regionMapper;

	@Override
	public int save(Region record) {
		record.setId(IdWorker.getId());
		return this.regionMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.regionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(Region record) {
		return this.regionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Region getById(Long id) {
		return this.regionMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Region> listForPage(int pageCurrent, int pageSize, RegionExample example) {
		int count = this.regionMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<Region>(count, totalPage, pageCurrent, pageSize, this.regionMapper.selectByExample(example));
	}

	@Override
	public List<Region> listByLevel(Integer level) {
		RegionExample example = new RegionExample();
		example.createCriteria().andLevelEqualTo(level);
		return this.regionMapper.selectByExample(example);
	}

	@Override
	public List<Region> listByProvinceId(Integer provinceId) {
		RegionExample example = new RegionExample();
		example.createCriteria().andProvinceIdEqualTo(provinceId);
		return this.regionMapper.selectByExample(example);
	}

	@Override
	public List<Region> listByCityId(Integer cityId) {
		RegionExample example = new RegionExample();
		example.createCriteria().andCityIdEqualTo(cityId);
		return this.regionMapper.selectByExample(example);
	}
	
}