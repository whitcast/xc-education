package com.xc.education.system.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.system.common.bean.qo.WebsiteNavArticleQO;
import com.xc.education.system.common.bean.vo.WebsiteNavArticleVO;
import com.xc.education.util.base.Page;
import com.xc.education.util.base.PageUtil;
import com.xc.education.system.service.dao.WebsiteNavArticleDao;
import com.xc.education.system.service.dao.impl.mapper.entity.WebsiteNavArticle;
import com.xc.education.system.service.dao.impl.mapper.entity.WebsiteNavArticleExample;
import com.xc.education.util.tools.BeanUtil;

/**
 * 站点导航文章 
 *
 * @author wuyun
 */
@Component
public class BossWebsiteNavArticleBiz {

	@Autowired
	private WebsiteNavArticleDao dao;

	public Page<WebsiteNavArticleVO> listForPage(WebsiteNavArticleQO qo) {
	    WebsiteNavArticleExample example = new WebsiteNavArticleExample();
	    example.setOrderByClause(" id desc ");
        Page<WebsiteNavArticle> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, WebsiteNavArticleVO.class);
	}

	public int save(WebsiteNavArticleQO qo) {
        WebsiteNavArticle record = BeanUtil.copyProperties(qo, WebsiteNavArticle.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public WebsiteNavArticleVO getById(Long id) {
	    WebsiteNavArticle record = dao.getById(id);
		return BeanUtil.copyProperties(record, WebsiteNavArticleVO.class);
	}

	public int updateById(WebsiteNavArticleQO qo) {
	    WebsiteNavArticle record = BeanUtil.copyProperties(qo, WebsiteNavArticle.class);
		return dao.updateById(record);
	}

	public WebsiteNavArticleVO getByNavId(Long navId) {
		WebsiteNavArticle record = dao.getByNavId(navId);
		return BeanUtil.copyProperties(record, WebsiteNavArticleVO.class);
	}
	
}
