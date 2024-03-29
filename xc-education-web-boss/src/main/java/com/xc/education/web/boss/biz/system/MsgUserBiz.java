package com.xc.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.system.common.bean.qo.MsgUserQO;
import com.xc.education.system.common.bean.vo.MsgUserVO;
import com.xc.education.system.feign.IBossMsgUser;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;

/**
 * 站内信用户记录表 
 *
 * @author wuyun
 */
@Component
public class MsgUserBiz extends BaseBiz{

	@Autowired
	private IBossMsgUser bossMsgUser;

	public Page<MsgUserVO> listForPage(MsgUserQO qo) {
        return bossMsgUser.listForPage(qo);
	}

	public int save(MsgUserQO qo) {
		return bossMsgUser.save(qo);
	}

	public int deleteById(Long id) {
		return bossMsgUser.deleteById(id);
	}

	public MsgUserVO getById(Long id) {
		return bossMsgUser.getById(id);
	}

	public int updateById(MsgUserQO qo) {
		return bossMsgUser.updateById(qo);
	}
	
}
