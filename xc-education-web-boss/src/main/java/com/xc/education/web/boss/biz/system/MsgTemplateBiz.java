package com.xc.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.system.common.bean.qo.MsgTemplateQO;
import com.xc.education.system.common.bean.vo.MsgTemplateVO;
import com.xc.education.system.feign.IBossMsgTemplate;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;

/**
 * 消息模板 
 *
 * @author wuyun
 */
@Component
public class MsgTemplateBiz extends BaseBiz{

	@Autowired
	private IBossMsgTemplate bossMsgTemplate;

	public Page<MsgTemplateVO> listForPage(MsgTemplateQO qo) {
        return bossMsgTemplate.listForPage(qo);
	}

	public int save(MsgTemplateQO qo) {
		return bossMsgTemplate.save(qo);
	}

	public int deleteById(Long id) {
		return bossMsgTemplate.deleteById(id);
	}

	public MsgTemplateVO getById(Long id) {
		return bossMsgTemplate.getById(id);
	}

	public int updateById(MsgTemplateQO qo) {
		return bossMsgTemplate.updateById(qo);
	}
	
}
