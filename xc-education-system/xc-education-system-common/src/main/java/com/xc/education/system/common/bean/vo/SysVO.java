package com.xc.education.system.common.bean.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 系统配置表
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class SysVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;
	/**
	 * 状态(1有效, 0无效)
	 */
	private Integer statusId;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 视频存储平台（1保利威视，2七牛）
	 */
	private Integer videoType;
	/**
	 * useid
	 */
	private String polyvUseid;
	/**
	 * writetoken
	 */
	private String polyvWritetoken;
	/**
	 * readtoken
	 */
	private String polyvReadtoken;
	/**
	 * secretkey
	 */
	private String polyvSecretkey;
	/**
	 * 文件存储类型（1阿里云，2七牛）
	 */
	private Integer fileType;
	/**
	 * access_key_id
	 */
	private String aliyunAccessKeyId;
	/**
	 * access_key_secret
	 */
	private String aliyunAccessKeySecret;
	/**
	 * 支付通道（1龙果支付，2其他）
	 */
	private Integer payType;
	/**
	 * xc_key
	 */
	private String xcKey;
	/**
	 * xc_secret
	 */
	private String xcSecret;
	/**
	 * oss_url
	 */
	private String aliyunOssUrl;
	/**
	 * oss_bucket
	 */
	private String aliyunOssBucket;
	/**
	 * pay_url
	 */
	private String payUrl;
	/**
	 * pay_key
	 */
	private String payKey;
	/**
	 * pay_secret
	 */
	private String paySecret;
	/**
	 * notifu_url
	 */
	private String notifyUrl;
	/**
	 * smsCode
	 */
	private String smsCode;
	/**
	 * signName
	 */
	private String signName;

}
