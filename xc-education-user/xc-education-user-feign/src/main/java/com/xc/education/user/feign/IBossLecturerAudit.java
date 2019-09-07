package com.xc.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.user.common.interfaces.BossLecturerAudit;

/**
 * 讲师信息-审核 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-user-service")
public interface IBossLecturerAudit extends BossLecturerAudit {

}
