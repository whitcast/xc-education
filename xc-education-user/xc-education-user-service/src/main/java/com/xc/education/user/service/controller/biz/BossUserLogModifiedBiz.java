package com.xc.education.user.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.user.common.bean.qo.UserLogModifiedQO;
import com.xc.education.user.common.bean.vo.UserLogModifiedVO;
import com.xc.education.user.service.dao.UserLogModifiedDao;
import com.xc.education.user.service.dao.impl.mapper.entity.UserLogModified;
import com.xc.education.user.service.dao.impl.mapper.entity.UserLogModifiedExample;
import com.xc.education.util.base.Page;
import com.xc.education.util.base.PageUtil;
import com.xc.education.util.tools.BeanUtil;

/**
 * 用户修改日志 
 *
 * @author wujing
 */
@Component
public class BossUserLogModifiedBiz {

	@Autowired
	private UserLogModifiedDao dao;

	public Page<UserLogModifiedVO> listForPage(UserLogModifiedQO qo) {
	    UserLogModifiedExample example = new UserLogModifiedExample();
	    example.setOrderByClause(" id desc ");
        Page<UserLogModified> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, UserLogModifiedVO.class);
	}

	public int save(UserLogModifiedQO qo) {
	    UserLogModified record = BeanUtil.copyProperties(qo, UserLogModified.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public UserLogModifiedVO getById(Long id) {
	    UserLogModified record = dao.getById(id);
		return BeanUtil.copyProperties(record, UserLogModifiedVO.class);
	}

	public int updateById(UserLogModifiedQO qo) {
	    UserLogModified record = BeanUtil.copyProperties(qo, UserLogModified.class);
		return dao.updateById(record);
	}
	
}
