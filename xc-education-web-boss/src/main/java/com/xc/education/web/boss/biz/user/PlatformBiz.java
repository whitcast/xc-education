package com.xc.education.web.boss.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.user.common.bean.qo.PlatformQO;
import com.xc.education.user.common.bean.vo.PlatformVO;
import com.xc.education.user.feign.IBossPlatform;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;
import com.xc.education.util.tools.StrUtil;

/**
 * 平台信息
 *
 * @author wujing
 */
@Component
public class PlatformBiz extends BaseBiz {

	@Autowired
	private IBossPlatform bossPlatform;

	public Page<PlatformVO> listForPage(PlatformQO qo) {
		return bossPlatform.listForPage(qo);
	}

	public int save(PlatformQO qo) {
		qo.setClientId("lk" + StrUtil.get32UUID());
		qo.setClientSecret(StrUtil.get32UUID());
		return bossPlatform.save(qo);
	}

	public int deleteById(Long id) {
		return bossPlatform.deleteById(id);
	}

	public PlatformVO getById(Long id) {
		return bossPlatform.getById(id);
	}

	public int updateById(PlatformQO qo) {
		return bossPlatform.updateById(qo);
	}

}
