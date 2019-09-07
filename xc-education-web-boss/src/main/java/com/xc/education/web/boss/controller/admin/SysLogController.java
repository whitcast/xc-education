/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.xc.education.web.boss.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xc.education.util.base.BaseController;
import com.xc.education.web.boss.common.bean.qo.SysLogQO;
import com.xc.education.web.boss.service.SysLogService;

/**
 * 后台操作日志表
 *
 * @author wujing
 * @since 2018-01-29
 */
@Controller
@RequestMapping(value = "/admin/sysLog")
public class SysLogController extends BaseController {

	@Autowired
	private SysLogService service;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute SysLogQO qo, ModelMap modelMap) {
		modelMap.put("page", service.listForPage(qo));
		modelMap.put("bean", qo);
	}

}
