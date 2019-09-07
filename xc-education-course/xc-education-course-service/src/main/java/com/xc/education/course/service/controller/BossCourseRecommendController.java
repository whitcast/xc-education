package com.xc.education.course.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xc.education.course.common.bean.qo.CourseRecommendQO;
import com.xc.education.course.common.bean.vo.CourseRecommendVO;
import com.xc.education.course.common.interfaces.BossCourseRecommend;
import com.xc.education.course.service.controller.biz.BossCourseRecommendBiz;
import com.xc.education.util.base.BaseController;
import com.xc.education.util.base.Page;

/**
 * 课程推荐
 *
 * @author wujing
 */
@RestController
public class BossCourseRecommendController extends BaseController implements BossCourseRecommend {

	@Autowired
	private BossCourseRecommendBiz biz;

	@Override
	public Page<CourseRecommendVO> listForPage(@RequestBody CourseRecommendQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody CourseRecommendQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@PathVariable(value = "id") Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody CourseRecommendQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public CourseRecommendVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

}
