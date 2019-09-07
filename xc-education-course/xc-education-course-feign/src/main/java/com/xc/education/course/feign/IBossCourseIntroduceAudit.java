package com.xc.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.course.common.interfaces.BossCourseIntroduceAudit;

/**
 * 课程介绍信息 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-course-service")
public interface IBossCourseIntroduceAudit extends BossCourseIntroduceAudit {

}
