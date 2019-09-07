package com.xc.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.user.common.interfaces.BossUserLogModified;

/**
 * 用户修改日志 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-user-service")
public interface IBossUserLogModified extends BossUserLogModified {

}
