package com.xc.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.xc.education.system.common.interfaces.BossWebsiteNavArticle;

/**
 * 站点导航文章 
 *
 * @author wuyun
 */
@FeignClient(value = "xc-education-system-service")
public interface IBossWebsiteNavArticle extends BossWebsiteNavArticle {

}
