package com.xc.education.web.boss.biz.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.xc.education.system.common.bean.qo.WebsiteNavArticleQO;
import com.xc.education.system.common.bean.vo.WebsiteNavArticleVO;
import com.xc.education.system.feign.IBossSys;
import com.xc.education.system.feign.IBossWebsiteNavArticle;
import com.xc.education.util.aliyun.Aliyun;
import com.xc.education.util.aliyun.AliyunUtil;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;
import com.xc.education.util.enums.PlatformEnum;
import com.xc.education.util.tools.BeanUtil;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
@Component
public class WebsiteNavArticleBiz extends BaseBiz {

	@Autowired
	private IBossSys bossSys;
	@Autowired
	private IBossWebsiteNavArticle bossWebsiteNavArticle;

	public Page<WebsiteNavArticleVO> listForPage(WebsiteNavArticleQO qo) {
		return bossWebsiteNavArticle.listForPage(qo);
	}

	public int save(WebsiteNavArticleQO qo, MultipartFile articleFile) {
		if (articleFile != null && !articleFile.isEmpty()) {
			qo.setArtPic(AliyunUtil.uploadPic(PlatformEnum.SYSTEM, articleFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		return bossWebsiteNavArticle.save(qo);
	}

	public int deleteById(Long id) {
		return bossWebsiteNavArticle.deleteById(id);
	}

	public WebsiteNavArticleVO getById(Long id) {
		return bossWebsiteNavArticle.getById(id);
	}

	public int updateById(WebsiteNavArticleQO qo, MultipartFile articleFile) {
		if (articleFile != null && !articleFile.isEmpty()) {
			qo.setArtPic(AliyunUtil.uploadPic(PlatformEnum.SYSTEM, articleFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
		}
		return bossWebsiteNavArticle.updateById(qo);
	}

	public WebsiteNavArticleVO getByNavId(Long navId) {
		return bossWebsiteNavArticle.getByNavId(navId);
	}

}
