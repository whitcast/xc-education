package com.xc.education.course.service.controller.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.xc.education.course.common.bean.qo.ZoneCourseQO;
import com.xc.education.course.common.bean.vo.ZoneCourseVO;
import com.xc.education.util.base.BaseException;
import com.xc.education.util.base.Page;
import com.xc.education.util.base.PageUtil;
import com.xc.education.util.enums.StatusIdEnum;
import com.xc.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xc.education.course.service.dao.CourseCategoryDao;
import com.xc.education.course.service.dao.CourseDao;
import com.xc.education.course.service.dao.ZoneCourseDao;
import com.xc.education.course.service.dao.impl.mapper.entity.Course;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseCategory;
import com.xc.education.course.service.dao.impl.mapper.entity.ZoneCourse;
import com.xc.education.course.service.dao.impl.mapper.entity.ZoneCourseExample;
import com.xc.education.course.service.dao.impl.mapper.entity.ZoneCourseExample.Criteria;

/**
 * 专区课程关联表
 *
 * @author wujing
 */
@Component
public class BossZoneCourseBiz {

	@Autowired
	private ZoneCourseDao dao;
	@Autowired
	private CourseCategoryDao courseCategoryDao;
	@Autowired
	private CourseDao courseDao;

	public Page<ZoneCourseVO> listForPage(ZoneCourseQO qo) {
		ZoneCourseExample example = new ZoneCourseExample();
		Criteria c = example.createCriteria();
		c.andZoneIdEqualTo(qo.getZoneId());
		if (!StringUtils.isEmpty(qo.getCourseName())) {
			Course course = courseDao.getByCourseName(qo.getCourseName());
			if (ObjectUtil.isNull(course)) {
				c.andCourseIdEqualTo(0L);
			}else {
				c.andCourseIdEqualTo(course.getId());
			}
		}
		example.setOrderByClause("status_id desc, sort desc, id desc");
		Page<ZoneCourse> listForPage = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		Page<ZoneCourseVO> page = PageUtil.transform(listForPage, ZoneCourseVO.class);
		// 根据课程ID获取课程信息
		for (ZoneCourseVO zoneCourseVO : page.getList()) {
			List<Course> course = courseDao.listByCategoryId(zoneCourseVO.getCourseId());
			for (Course courseinfo : course) {
				// 获取课程所属分类
				if (courseinfo.getCategoryId1() != null && courseinfo.getCategoryId1() != 0) {
					CourseCategory courseCategory = courseCategoryDao.getById(courseinfo.getCategoryId1());
					if (courseCategory != null) {
						zoneCourseVO.setCategoryName1(courseCategory.getCategoryName());
					}
				}
				if (courseinfo.getCategoryId2() != null && courseinfo.getCategoryId2() != 0) {
					CourseCategory courseCategory = courseCategoryDao.getById(courseinfo.getCategoryId2());
					if (courseCategory != null) {
						zoneCourseVO.setCategoryName2(courseCategory.getCategoryName());
					}
				}
				if (courseinfo.getCategoryId3() != null && courseinfo.getCategoryId3() != 0) {
					CourseCategory courseCategory = courseCategoryDao.getById(courseinfo.getCategoryId3());
					if (courseCategory != null) {
						zoneCourseVO.setCategoryName3(courseCategory.getCategoryName());
					}
				}
				zoneCourseVO.setCourseName(courseinfo.getCourseName());
			}

		}
		return page;
	}

	public int save(ZoneCourseQO qo) {
		if (ObjectUtil.isNull(qo.getCourseId())) {
			throw new BaseException("课程编号不能为空");
		}
		if (ObjectUtil.isNull(qo.getZoneId())) {
			throw new BaseException("专区编号不能为空");
		}
		ZoneCourse zoneCourse = dao.getZoneIdAndCourseId(qo.getCourseId(), qo.getZoneId());
		if (!ObjectUtil.isNull(zoneCourse)) {
			throw new BaseException("已存在专区课程");
		}
		// 根据课程编号获取课程信息
		Course course = courseDao.getByCourseIdAndStatusId(qo.getCourseId(), StatusIdEnum.YES.getCode());
		if (ObjectUtil.isNull(course)) {
			throw new BaseException("找不到课程信息");
		}
		// 保存分区关联课程信息
		ZoneCourse result = new ZoneCourse();
		result.setCourseId(course.getId());
		result.setZoneId(qo.getZoneId());
		result.setSort(1);
		return dao.save(result);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public ZoneCourseVO getById(Long id) {
		ZoneCourse record = dao.getById(id);
		return BeanUtil.copyProperties(record, ZoneCourseVO.class);
	}

	public int updateById(ZoneCourseQO qo) {
		ZoneCourse record = BeanUtil.copyProperties(qo, ZoneCourse.class);
		return dao.updateById(record);
	}

}
