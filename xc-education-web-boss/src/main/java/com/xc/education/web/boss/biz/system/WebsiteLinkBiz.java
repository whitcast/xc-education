package com.xc.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.system.common.bean.qo.WebsiteLinkQO;
import com.xc.education.system.common.bean.vo.WebsiteLinkVO;
import com.xc.education.system.feign.IBossWebsiteLink;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;

/**
 * 站点友情链接 
 *
 * @author wuyun
 */
@Component
public class WebsiteLinkBiz extends BaseBiz{

	@Autowired
	private IBossWebsiteLink bossWebsiteLink;

	public Page<WebsiteLinkVO> listForPage(WebsiteLinkQO qo) {
        return bossWebsiteLink.listForPage(qo);
	}

	public int save(WebsiteLinkQO qo) {
		return bossWebsiteLink.save(qo);
	}

	public int deleteById(Long id) {
		return bossWebsiteLink.deleteById(id);
	}

	public WebsiteLinkVO getById(Long id) {
		return bossWebsiteLink.getById(id);
	}

	public int updateById(WebsiteLinkQO qo) {
		return bossWebsiteLink.updateById(qo);
	}

	public int updateStatusId(WebsiteLinkQO qo) {
		return bossWebsiteLink.updateById(qo);
	}
	
}
