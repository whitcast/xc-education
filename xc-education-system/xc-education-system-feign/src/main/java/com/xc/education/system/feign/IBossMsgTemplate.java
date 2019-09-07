package com.xc.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.system.common.interfaces.BossMsgTemplate;


/**
 * 消息模板 
 *
 * @author wuyun
 */
@FeignClient(value = "xc-education-system-service")
public interface IBossMsgTemplate extends BossMsgTemplate {

}
