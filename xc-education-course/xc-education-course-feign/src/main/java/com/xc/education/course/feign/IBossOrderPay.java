package com.xc.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.course.common.interfaces.BossOrderPay;

/**
 * 订单支付信息表 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-course-service")
public interface IBossOrderPay extends BossOrderPay {

}
