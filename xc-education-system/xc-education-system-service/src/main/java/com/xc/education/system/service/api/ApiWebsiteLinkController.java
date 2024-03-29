package com.xc.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xc.education.system.service.biz.ApiWebsiteLinkBiz;
import com.xc.education.system.service.common.dto.WebsiteLinkListDTO;
import com.xc.education.util.base.BaseController;
import com.xc.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@RestController
public class ApiWebsiteLinkController extends BaseController {

	@Autowired
	private ApiWebsiteLinkBiz biz;

	/**
	 * 获取站点友情链接接口
	 * 
	 * @return 站点友情链接
	 * @author wuyun
	 */
	@ApiOperation(value = "获取站点友情链接接口", notes = "获取站点友情链接")
	@RequestMapping(value = "/system/api/website/link", method = RequestMethod.POST)
	public Result<WebsiteLinkListDTO> list() {
		return biz.list();
	}

}
