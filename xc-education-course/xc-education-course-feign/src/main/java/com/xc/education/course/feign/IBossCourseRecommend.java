package com.xc.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.course.common.interfaces.BossCourseRecommend;

/**
 * 课程推荐
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-course-service")
public interface IBossCourseRecommend extends BossCourseRecommend {

}
