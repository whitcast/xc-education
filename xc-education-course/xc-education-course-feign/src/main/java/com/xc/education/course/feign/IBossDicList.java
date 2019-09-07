package com.xc.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.course.common.interfaces.BossDicList;

/**
 * 数据字典明细表 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-course-service")
public interface IBossDicList extends BossDicList {

}
