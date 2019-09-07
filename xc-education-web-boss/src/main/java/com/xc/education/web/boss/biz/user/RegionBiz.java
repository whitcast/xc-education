package com.xc.education.web.boss.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.user.common.bean.qo.RegionQO;
import com.xc.education.user.common.bean.vo.RegionVO;
import com.xc.education.user.feign.IBossRegion;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;

/**
 * 行政区域表 
 *
 * @author wujing
 */
@Component
public class RegionBiz extends BaseBiz{

	@Autowired
	private IBossRegion bossRegion;

	public Page<RegionVO> listForPage(RegionQO qo) {
        return bossRegion.listForPage(qo);
	}

	public int save(RegionQO qo) {
		return bossRegion.save(qo);
	}

	public int deleteById(Long id) {
		return bossRegion.deleteById(id);
	}

	public RegionVO getById(Long id) {
		return bossRegion.getById(id);
	}

	public int updateById(RegionQO qo) {
		return bossRegion.updateById(qo);
	}
	
}
