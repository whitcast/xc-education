package com.xc.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.user.common.interfaces.BossSendSmsLog;

/**
 * 用户发送短信日志
 *
 * @author YZJ
 */
@FeignClient(value = "xc-education-user-service")
public interface IBossSendSmsLog extends BossSendSmsLog {

}
