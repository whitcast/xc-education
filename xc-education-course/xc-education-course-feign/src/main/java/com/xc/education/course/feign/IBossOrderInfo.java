package com.xc.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.course.common.interfaces.BossOrderInfo;

/**
 * 订单信息表 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-course-service")
public interface IBossOrderInfo extends BossOrderInfo {

}
