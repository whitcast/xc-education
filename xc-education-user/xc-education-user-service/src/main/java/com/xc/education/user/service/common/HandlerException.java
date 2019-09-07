package com.xc.education.user.service.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.xc.education.util.base.BaseController;
import com.xc.education.util.base.BaseException;
import com.xc.education.util.base.Result;

/**
 * 
 * @author wujing
 */
@RestControllerAdvice(basePackages = "com.xc.education.user.service.controller.gateway")
public class HandlerException extends BaseController {

	@ExceptionHandler({ BaseException.class })
	@ResponseStatus(HttpStatus.OK)
	public Result<String> processException(BaseException e) {
		logger.error("BaseException", e);
		return Result.error(e.getMessage());
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public Result<String> processException(Exception e) {
		logger.error("Exception", e);
		return Result.error("系统错误");
	}

}
