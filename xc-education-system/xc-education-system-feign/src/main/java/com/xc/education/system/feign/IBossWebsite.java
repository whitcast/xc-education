package com.xc.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.system.common.interfaces.BossWebsite;

/**
 * 站点信息 
 *
 * @author wuyun
 */
@FeignClient(value = "xc-education-system-service")
public interface IBossWebsite extends BossWebsite {

}
