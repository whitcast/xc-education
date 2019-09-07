package com.xc.education.system.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xc.education.system.service.biz.auth.AuthApiMsgBiz;
import com.xc.education.system.service.common.bo.MsgViewBO;
import com.xc.education.system.service.common.dto.MsgDTO;
import com.xc.education.util.base.BaseController;
import com.xc.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@RestController
@RequestMapping(value = "/auth/user/system/api/msg")
public class AuthApiMsg extends BaseController {

	@Autowired
	private AuthApiMsgBiz biz;

	/**
	 * 课站内信详情
	 */
	@ApiOperation(value = "站内信详情", notes = "站内信详情接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	Result<MsgDTO> view(@RequestBody MsgViewBO MsgViewBO) {
		return biz.view(MsgViewBO);
	};
}
