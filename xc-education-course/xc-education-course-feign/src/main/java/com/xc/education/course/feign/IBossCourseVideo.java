package com.xc.education.course.feign;

import com.xc.education.course.common.interfaces.BossCourseVideo;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 课程视频信息 
 *
 * @author wuyun
 */
@FeignClient(value = "xc-education-course-service")
public interface IBossCourseVideo extends BossCourseVideo {

}
