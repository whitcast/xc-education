package com.xc.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.system.common.interfaces.BossSys;

/**
 * 系统配置表 
 *
 * @author YZJ
 */
@FeignClient(value = "xc-education-system-service")
public interface IBossSys extends BossSys {

}
