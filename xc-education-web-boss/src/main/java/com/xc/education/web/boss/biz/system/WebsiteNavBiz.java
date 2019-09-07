package com.xc.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.system.common.bean.qo.WebsiteNavQO;
import com.xc.education.system.common.bean.vo.WebsiteNavVO;
import com.xc.education.system.feign.IBossWebsiteNav;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;

/**
 * 站点导航 
 *
 * @author wuyun
 */
@Component
public class WebsiteNavBiz extends BaseBiz{

	@Autowired
	private IBossWebsiteNav bossWebsiteNav;

	public Page<WebsiteNavVO> listForPage(WebsiteNavQO qo) {
        return bossWebsiteNav.listForPage(qo);
	}

	public int save(WebsiteNavQO qo) {
		return bossWebsiteNav.save(qo);
	}

	public int deleteById(Long id) {
		return bossWebsiteNav.deleteById(id);
	}

	public WebsiteNavVO getById(Long id) {
		return bossWebsiteNav.getById(id);
	}

	public int updateById(WebsiteNavQO qo) {
		return bossWebsiteNav.updateById(qo);
	}

	public int updateStatusId(WebsiteNavQO qo) {
		return bossWebsiteNav.updateById(qo);
	}

}
