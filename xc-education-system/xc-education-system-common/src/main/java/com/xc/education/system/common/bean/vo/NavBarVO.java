package com.xc.education.system.common.bean.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 头部导航
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class NavBarVO implements Serializable {

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
     * 导航标题
     */
    private String navTitle;
    /**
     * 导航url
     */
    private String navUrl;
    /**
     * 跳转方式
     */
    private String target;

}
