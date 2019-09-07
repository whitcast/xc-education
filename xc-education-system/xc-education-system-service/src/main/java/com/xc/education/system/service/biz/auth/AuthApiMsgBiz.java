package com.xc.education.system.service.biz.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.xc.education.system.service.common.bo.MsgViewBO;
import com.xc.education.system.service.common.dto.MsgDTO;
import com.xc.education.system.service.dao.MsgDao;
import com.xc.education.system.service.dao.impl.mapper.entity.Msg;
import com.xc.education.util.base.Result;
import com.xc.education.util.tools.BeanUtil;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@Component
public class AuthApiMsgBiz {

	@Autowired
	private MsgDao dao;

	public Result<MsgDTO> view(MsgViewBO bo) {
		if (bo.getId() == null) {
			return Result.error("id不能为空");
		}
		Msg msg = dao.getById(bo.getId());
		if (ObjectUtils.isEmpty(msg)) {
			return Result.error("查询错误");
		}
		return Result.success(BeanUtil.copyProperties(msg, MsgDTO.class));
	}
}
