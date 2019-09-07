package com.xc.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.user.common.interfaces.BossLecturerExt;

/**
 * 讲师账户信息表 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-user-service")
public interface IBossLecturerExt extends BossLecturerExt {

}
