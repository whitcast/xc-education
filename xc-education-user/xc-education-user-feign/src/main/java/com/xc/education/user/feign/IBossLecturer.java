package com.xc.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.user.common.interfaces.BossLecturer;

/**
 * 讲师信息
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-user-service")
public interface IBossLecturer extends BossLecturer {

}
