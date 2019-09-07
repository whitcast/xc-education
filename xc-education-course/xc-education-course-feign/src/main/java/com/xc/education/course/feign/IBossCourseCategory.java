package com.xc.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.course.common.interfaces.BossCourseCategory;

/**
 * 课程分类 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-course-service")
public interface IBossCourseCategory extends BossCourseCategory {

}
