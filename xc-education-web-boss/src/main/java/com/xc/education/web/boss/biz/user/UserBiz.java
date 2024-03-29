package com.xc.education.web.boss.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.user.common.bean.qo.UserQO;
import com.xc.education.user.common.bean.vo.UserVO;
import com.xc.education.user.feign.IBossUser;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;

/**
 * 用户基本信息 
 *
 * @author wujing
 */
@Component
public class UserBiz extends BaseBiz{

	@Autowired
	private IBossUser bossUser;

	public Page<UserVO> listForPage(UserQO qo) {
        return bossUser.listForPage(qo);
	}

	public int save(UserQO qo) {
		return bossUser.save(qo);
	}

	public int deleteById(Long id) {
		return bossUser.deleteById(id);
	}

	public UserVO getById(Long id) {
		return bossUser.getById(id);
	}

	public int updateById(UserQO qo) {
		return bossUser.updateById(qo);
	}
	
}
