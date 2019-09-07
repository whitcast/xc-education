package com.xc.education.web.boss.biz.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.course.common.bean.qo.DicListQO;
import com.xc.education.course.common.bean.vo.DicListVO;
import com.xc.education.course.feign.IBossDicList;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;

/**
 * 数据字典明细表 
 *
 * @author wujing
 */
@Component
public class DicListBiz extends BaseBiz{

	@Autowired
	private IBossDicList bossDicList;

	public Page<DicListVO> listForPage(DicListQO qo) {
        return bossDicList.listForPage(qo);
	}

	public int save(DicListQO qo) {
		return bossDicList.save(qo);
	}

	public int deleteById(Long id) {
		return bossDicList.deleteById(id);
	}

	public DicListVO getById(Long id) {
		return bossDicList.getById(id);
	}

	public int updateById(DicListQO qo) {
		return bossDicList.updateById(qo);
	}
	
}
