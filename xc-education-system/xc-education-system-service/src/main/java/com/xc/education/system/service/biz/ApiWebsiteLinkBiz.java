package com.xc.education.system.service.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.system.service.common.dto.WebsiteLinkDTO;
import com.xc.education.system.service.common.dto.WebsiteLinkListDTO;
import com.xc.education.system.service.dao.WebsiteLinkDao;
import com.xc.education.system.service.dao.impl.mapper.entity.WebsiteLink;
import com.xc.education.util.base.PageUtil;
import com.xc.education.util.base.Result;
import com.xc.education.util.enums.StatusIdEnum;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@Component
public class ApiWebsiteLinkBiz {

	@Autowired
	private WebsiteLinkDao dao;

	public Result<WebsiteLinkListDTO> list() {
		List<WebsiteLink> websiteLinkList = dao.listByStatusId(StatusIdEnum.YES.getCode());
		WebsiteLinkListDTO dto = new WebsiteLinkListDTO();
		dto.setWebsiteLinkList(PageUtil.copyList(websiteLinkList, WebsiteLinkDTO.class));
		return Result.success(dto);
	}

}
