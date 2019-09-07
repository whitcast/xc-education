package com.xc.education.system.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.xc.education.system.common.bean.qo.NavBarQO;
import com.xc.education.system.common.bean.vo.NavBarVO;
import com.xc.education.system.common.interfaces.BossNavBar;
import com.xc.education.system.service.controller.biz.BossNavBarBiz;
import com.xc.education.util.base.BaseController;
import com.xc.education.util.base.Page;

/**
 * 头部导航
 *
 * @author wuyun
 */
@RestController
public class BossNavBarController extends BaseController implements BossNavBar {

	@Autowired
	private BossNavBarBiz biz;

	@Override
	public Page<NavBarVO> listForPage(@RequestBody NavBarQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody NavBarQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@RequestBody Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody NavBarQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public NavBarVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

}
