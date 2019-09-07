package com.xc.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xc.education.system.service.biz.ApiWebsiteBiz;
import com.xc.education.system.service.common.dto.WebsiteDTO;
import com.xc.education.util.base.BaseController;
import com.xc.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 站点信息
 *
 * @author wuyun
 */
@RestController
public class ApiWebsiteController extends BaseController {

	@Autowired
	private ApiWebsiteBiz biz;

	/**
	 * 获取站点信息接口
	 * 
	 * @return 站点信息
	 * @author wuyun
	 */
	@ApiOperation(value = "获取站点信息接口", notes = "获取站点信息")
	@RequestMapping(value = "/system/api/website/get", method = RequestMethod.POST)
	public Result<WebsiteDTO> get() {
		return biz.get();
	}

}
