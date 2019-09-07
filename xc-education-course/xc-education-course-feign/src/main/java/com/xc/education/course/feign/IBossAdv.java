package com.xc.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.course.common.interfaces.BossAdv;

/**
 * 广告信息 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-course-service")
public interface IBossAdv extends BossAdv {

}
