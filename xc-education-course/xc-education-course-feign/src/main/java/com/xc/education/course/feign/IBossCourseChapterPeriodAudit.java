package com.xc.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.course.common.interfaces.BossCourseChapterPeriodAudit;

/**
 * 课时信息-审核 
 *
 * @author wujing
 */
@FeignClient(value = "xc-education-course-service")
public interface IBossCourseChapterPeriodAudit extends BossCourseChapterPeriodAudit {

}
