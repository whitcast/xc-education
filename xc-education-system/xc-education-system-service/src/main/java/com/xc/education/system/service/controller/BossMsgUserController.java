package com.xc.education.system.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xc.education.system.common.bean.qo.MsgUserQO;
import com.xc.education.system.common.bean.vo.MsgUserVO;
import com.xc.education.system.common.interfaces.BossMsgUser;
import com.xc.education.system.service.controller.biz.BossMsgUserBiz;
import com.xc.education.util.base.BaseController;
import com.xc.education.util.base.Page;


/**
 * 站内信用户记录表 
 *
 * @author wuyun
 */
@RestController
public class BossMsgUserController extends BaseController implements BossMsgUser{

	@Autowired
	private BossMsgUserBiz biz;
	
	@Override
	public Page<MsgUserVO> listForPage(@RequestBody MsgUserQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody MsgUserQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@RequestBody Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody MsgUserQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public MsgUserVO getById(@RequestBody Long id){
		return biz.getById(id);
	}
    
}
