package com.xc.education.system.common.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xc.education.system.common.bean.qo.MsgTemplateQO;
import com.xc.education.system.common.bean.vo.MsgTemplateVO;
import com.xc.education.util.base.Page;

/**
 * 消息模板 
 *
 * @author itheima
 */
public interface BossMsgTemplate {
	
	@RequestMapping(value = "/boss/system/msgTemplate/listForPage")
	Page<MsgTemplateVO> listForPage(@RequestBody MsgTemplateQO qo);
	
	@RequestMapping(value = "/boss/system/msgTemplate/save")
	int save(@RequestBody MsgTemplateQO qo);
	
	@RequestMapping(value = "/boss/system/msgTemplate/deleteById")
	int deleteById(@RequestBody Long id);
	
	@RequestMapping(value = "/boss/system/msgTemplate/updateById")
	int updateById(@RequestBody MsgTemplateQO qo);
	
	@RequestMapping(value = "/boss/system/msgTemplate/getById")
	MsgTemplateVO getById(@RequestBody Long id);
	
}
