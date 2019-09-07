package com.xc.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.user.common.interfaces.BossUserExt;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-user-service")
public interface IBossUserExt extends BossUserExt {

}
