/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.xc.education.web.boss.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.util.base.Page;
import com.xc.education.util.base.PageUtil;
import com.xc.education.util.tools.DateUtil;
import com.xc.education.web.boss.common.bean.qo.SysLogQO;
import com.xc.education.web.boss.common.bean.vo.SysLogVO;
import com.xc.education.web.boss.service.dao.SysLogDao;
import com.xc.education.web.boss.service.dao.impl.mapper.entity.SysLog;
import com.xc.education.web.boss.service.dao.impl.mapper.entity.SysLogExample;
import com.xc.education.web.boss.service.dao.impl.mapper.entity.SysLogExample.Criteria;

/**
 * 后台操作日志表
 */
@Component
public class SysLogService {

	@Autowired
	private SysLogDao sysLogDao;

	public Page<SysLogVO> listForPage(SysLogQO qo) {
	    SysLogExample example = new SysLogExample();
	    Criteria c = example.createCriteria();
	    if (StringUtils.isNotEmpty(qo.getRealName())){
	    	c.andRealNameLike(PageUtil.like(qo.getRealName()));
	    }
	    if (StringUtils.isNotEmpty(qo.getBeginDate())){
			c.andGmtCreateGreaterThanOrEqualTo(DateUtil.parseDate(qo.getBeginDate(),"yyyy-MM-dd"));
		}
		if (StringUtils.isNotEmpty(qo.getEndDate())){
			c.andGmtCreateLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(qo.getEndDate(),"yyyy-MM-dd"), 1));
		}
	    example.setOrderByClause(" id desc ");
        Page<SysLog> page = sysLogDao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, SysLogVO.class);
	}

}
