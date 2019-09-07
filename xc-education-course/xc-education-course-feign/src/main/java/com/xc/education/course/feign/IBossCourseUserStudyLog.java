package com.xc.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.course.common.interfaces.BossCourseUserStudyLog;

/**
 * 课程用户学习日志 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-course-service")
public interface IBossCourseUserStudyLog extends BossCourseUserStudyLog {

}
