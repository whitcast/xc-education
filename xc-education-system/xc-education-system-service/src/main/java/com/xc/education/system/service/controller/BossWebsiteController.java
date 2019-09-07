package com.xc.education.system.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.xc.education.system.common.bean.qo.WebsiteQO;
import com.xc.education.system.common.bean.vo.WebsiteVO;
import com.xc.education.system.common.interfaces.BossWebsite;
import com.xc.education.system.service.controller.biz.BossWebsiteBiz;
import com.xc.education.util.base.BaseController;
import com.xc.education.util.base.Page;

/**
 * 站点信息
 *
 * @author wuyun
 */
@RestController
public class BossWebsiteController extends BaseController implements BossWebsite {

	@Autowired
	private BossWebsiteBiz biz;

	@Override
	public Page<WebsiteVO> listForPage(@RequestBody WebsiteQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody WebsiteQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@RequestBody Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody WebsiteQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public WebsiteVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

	@Override
	public WebsiteVO getWebsite() {
		return biz.getWebsite();
	}

}
