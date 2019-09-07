package com.xc.education.user.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xc.education.user.service.biz.auth.AuthApiLecturerBiz;
import com.xc.education.user.service.common.bo.LecturerViewBO;
import com.xc.education.user.service.common.dto.LecturerViewDTO;
import com.xc.education.util.base.BaseController;
import com.xc.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/auth/user/api/lecturer")
public class AuthApiLecturerController extends BaseController {

	@Autowired
	private AuthApiLecturerBiz biz;

	/**
	 * 讲师信息查看接口
	 * 
	 * @param lecturerUserNo
	 * @author wuyun
	 */
	@ApiOperation(value = "讲师查看接口", notes = "根据讲师用户编号查看讲师信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<LecturerViewDTO> view(@RequestBody LecturerViewBO lecturerViewBO) {
		return biz.view(lecturerViewBO);
	}

}
