package com.xc.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.user.common.interfaces.BossRegion;

/**
 * 行政区域表 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-user-service")
public interface IBossRegion extends BossRegion {

}
