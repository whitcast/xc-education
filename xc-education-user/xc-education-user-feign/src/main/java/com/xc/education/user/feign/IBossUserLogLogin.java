package com.xc.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.user.common.interfaces.BossUserLogLogin;

/**
 * 用户错误登录日志 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-user-service")
public interface IBossUserLogLogin extends BossUserLogLogin {

}
