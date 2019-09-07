package com.xc.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.system.common.interfaces.BossNavBar;

/**
 * 头部导航 
 *
 * @author wuyun
 */
@FeignClient(value = "xc-education-system-service")
public interface IBossNavBar extends BossNavBar {

}
