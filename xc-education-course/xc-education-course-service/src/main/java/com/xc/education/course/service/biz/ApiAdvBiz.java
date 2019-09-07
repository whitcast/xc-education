package com.xc.education.course.service.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.course.service.common.bo.AdvBO;
import com.xc.education.course.service.common.dto.AdvDTO;
import com.xc.education.course.service.common.dto.AdvListDTO;
import com.xc.education.course.service.dao.AdvDao;
import com.xc.education.course.service.dao.impl.mapper.entity.Adv;
import com.xc.education.util.base.PageUtil;
import com.xc.education.util.base.Result;
import com.xc.education.util.enums.StatusIdEnum;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * 广告信息
 *
 * @author wujing
 */
@Component
public class ApiAdvBiz {

	@Autowired
	private AdvDao advDao;

	public Result<AdvListDTO> list(AdvBO advBO) {
		AdvListDTO dto = new AdvListDTO();
		// 开始时间和结束时间
		List<Adv> advList = advDao.listByPlatShowAndStatusId(advBO.getPlatShow(), StatusIdEnum.YES.getCode());
		if (CollectionUtil.isEmpty(advList)) {
			return Result.error("找不到广告信息");
		}
		dto.setAdvList(PageUtil.copyList(advList, AdvDTO.class));
		return Result.success(dto);
	}

}
