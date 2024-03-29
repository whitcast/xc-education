package com.xc.education.system.service.common.bo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class MsgViewBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;

}
