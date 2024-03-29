package com.xc.education.course.service.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class AuthCourseVideoUpdateBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 课时ID
	 */
	@ApiModelProperty(value = "课时ID")
	private Long periodId;

	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	private Long userNo;

	/**
	 * 视频编号
	 */
	@ApiModelProperty(value = "视频编号")
	private Long videoNo;
}
