package com.xc.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.system.common.bean.qo.MsgQO;
import com.xc.education.system.common.bean.vo.MsgVO;
import com.xc.education.system.feign.IBossMsg;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@Component
public class MsgBiz extends BaseBiz {

	@Autowired
	private IBossMsg bossMsg;

	public Page<MsgVO> listForPage(MsgQO qo) {
		return bossMsg.listForPage(qo);
	}

	public int save(MsgQO qo) {
		return bossMsg.save(qo);
	}

	public int deleteById(Long id) {
		return bossMsg.deleteById(id);
	}

	public MsgVO getById(Long id) {
		return bossMsg.getById(id);
	}

	public int updateById(MsgQO qo) {
		return bossMsg.updateById(qo);
	}

	public int pushByManual(Long id) {
		return bossMsg.pushByManual(id);
	}

}
