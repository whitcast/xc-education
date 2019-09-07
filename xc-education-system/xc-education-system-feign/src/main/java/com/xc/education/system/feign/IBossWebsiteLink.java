package com.xc.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.system.common.interfaces.BossWebsiteLink;

/**
 * 站点友情链接 
 *
 * @author wuyun
 */
@FeignClient(value = "xc-education-system-service")
public interface IBossWebsiteLink extends BossWebsiteLink {

}
