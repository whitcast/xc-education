package com.xc.education.system.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.xc.education.system.common.bean.qo.WebsiteLinkQO;
import com.xc.education.system.common.bean.vo.WebsiteLinkVO;
import com.xc.education.system.common.interfaces.BossWebsiteLink;
import com.xc.education.system.service.controller.biz.BossWebsiteLinkBiz;
import com.xc.education.util.base.BaseController;
import com.xc.education.util.base.Page;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@RestController
public class BossWebsiteLinkController extends BaseController implements BossWebsiteLink {

	@Autowired
	private BossWebsiteLinkBiz biz;

	@Override
	public Page<WebsiteLinkVO> listForPage(@RequestBody WebsiteLinkQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody WebsiteLinkQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@RequestBody Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody WebsiteLinkQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public WebsiteLinkVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

}
