package com.xc.education.course.service.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseViewBO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户NO
	 */
	@ApiModelProperty(value = "用户NO", required = true)
	private Long userNo;
	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID", required = true)
	private Long courseId;

}
