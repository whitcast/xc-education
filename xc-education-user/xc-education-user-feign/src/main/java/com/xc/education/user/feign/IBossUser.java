package com.xc.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.user.common.interfaces.BossUser;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-user-service")
public interface IBossUser extends BossUser {

}
