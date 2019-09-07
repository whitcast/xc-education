package com.xc.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.system.common.interfaces.BossMsgUser;


/**
 * 站内信用户记录表 
 *
 * @author wuyun
 */
@FeignClient(value = "xc-education-system-service")
public interface IBossMsgUser extends BossMsgUser {

}
