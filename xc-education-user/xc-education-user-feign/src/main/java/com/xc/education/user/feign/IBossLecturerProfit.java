package com.xc.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.user.common.interfaces.BossLecturerProfit;

/**
 * 讲师提现日志表 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-user-service")
public interface IBossLecturerProfit extends BossLecturerProfit {

}
