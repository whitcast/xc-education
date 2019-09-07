package com.xc.education.system.service.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.system.service.common.dto.NavBarDTO;
import com.xc.education.system.service.common.dto.NavBarListDTO;
import com.xc.education.system.service.dao.NavBarDao;
import com.xc.education.system.service.dao.impl.mapper.entity.NavBar;
import com.xc.education.util.base.Result;
import com.xc.education.util.enums.StatusIdEnum;
import com.xc.education.util.tools.ArrayListUtil;

/**
 * 头部导航
 *
 * @author wuyun
 */
@Component
public class ApiNavBarBiz {

	@Autowired
	private NavBarDao navBarDao;

	public Result<NavBarListDTO> list() {
		List<NavBar> list = navBarDao.getByStatusId(StatusIdEnum.YES.getCode());
		NavBarListDTO dto = new NavBarListDTO();
		dto.setList(ArrayListUtil.copy(list, NavBarDTO.class));
		return Result.success(dto);
	}

}
