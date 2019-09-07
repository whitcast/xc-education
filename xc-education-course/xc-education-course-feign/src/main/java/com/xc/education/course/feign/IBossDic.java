package com.xc.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.course.common.interfaces.BossDic;

/**
 * 数据字典 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-course-service")
public interface IBossDic extends BossDic {

}
