package com.xc.education.course.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xc.education.course.service.biz.auth.AuthApiCourseUserStudyLogBiz;
import com.xc.education.course.service.common.bo.auth.AuthCourseUserStudyLogPageBO;
import com.xc.education.course.service.common.dto.auth.AuthCourseUserStudyLogPageDTO;
import com.xc.education.util.base.BaseController;
import com.xc.education.util.base.Page;
import com.xc.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/auth/course/api/course/user/study/log")
public class AuthApiCourseUserStudyLogController extends BaseController {

	@Autowired
	private AuthApiCourseUserStudyLogBiz biz;

	/**
	 * 最近学习日志分页列出接口
	 * 
	 * @param authCourseUserStudyLogPageBO
	 * @return
	 * @author wuyun
	 */
	@ApiOperation(value = "分页列出接口", notes = "最近学习分页列出接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<AuthCourseUserStudyLogPageDTO>> list(@RequestBody AuthCourseUserStudyLogPageBO authCourseUserStudyLogPageBO) {
		return biz.list(authCourseUserStudyLogPageBO);
	}

}
