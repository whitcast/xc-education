package com.xc.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.course.common.interfaces.BossZoneCourse;

/**
 * 专区课程关联表 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-course-service")
public interface IBossZoneCourse extends BossZoneCourse {

}
