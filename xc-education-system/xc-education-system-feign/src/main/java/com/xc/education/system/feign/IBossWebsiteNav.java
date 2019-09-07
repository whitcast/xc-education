package com.xc.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.system.common.interfaces.BossWebsiteNav;

/**
 * 站点导航 
 *
 * @author wuyun
 */
@FeignClient(value = "xc-education-system-service")
public interface IBossWebsiteNav extends BossWebsiteNav {

}
