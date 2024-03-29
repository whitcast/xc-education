package com.xc.education.course.service.common.bo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区课程关联表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class ZoneCourseBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 专区编号
	 */
	private Long zoneId;
}
