package com.xc.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.system.common.interfaces.BossMsg;


/**
 * 站内信息表 
 *
 * @author wuyun
 */
@FeignClient(value = "xc-education-system-service")
public interface IBossMsg extends BossMsg {

}
