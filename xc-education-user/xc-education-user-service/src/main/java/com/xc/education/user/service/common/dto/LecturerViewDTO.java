package com.xc.education.user.service.common.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 讲师信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class LecturerViewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	/**
	 * 讲师编号
	 */
	@ApiModelProperty(value = "讲师编号")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long lecturerUserNo;
	/**
	 * 讲师名称
	 */
	@ApiModelProperty(value = "讲师名称")
	private String lecturerName;
	/**
	 * 电话
	 */
	@ApiModelProperty(value = "电话")
	private String lecturerMobile;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
	private String headImgUrl;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	private String lecturerEmail;
	/**
	 * 职位
	 */
	@ApiModelProperty(value = "职位")
	private String position;
	/**
	 * 简介
	 */
	@ApiModelProperty(value = "简介")
	private String introduce;
}
