package com.xc.education.course.service.biz;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.xc.education.course.service.common.dto.CourseCategoryDTO;
import com.xc.education.course.service.common.dto.CourseCategoryListDTO;
import com.xc.education.course.service.common.dto.CourseCategoryThreeDTO;
import com.xc.education.course.service.common.dto.CourseCategoryTwoDTO;
import com.xc.education.course.service.dao.CourseCategoryDao;
import com.xc.education.course.service.dao.impl.mapper.entity.CourseCategory;
import com.xc.education.util.enums.CategoryTypeEnum;
import com.xc.education.util.enums.StatusIdEnum;
import com.xc.education.util.tools.ArrayListUtil;
import com.xc.education.util.tools.BeanUtil;
import com.xc.education.util.base.Result;

/**
 * 课程分类
 *
 * @author wujing
 */
@Component
public class ApiCourseCategoryBiz {

	@Autowired
	private CourseCategoryDao dao;

	/**
	 * 获取课程分类列表
	 * 
	 * @return
	 * @author wuyun
	 */
	public Result<CourseCategoryListDTO> list() {
		CourseCategoryListDTO dto = new CourseCategoryListDTO();
		// 根据分类类型、层级查询可用状态的课程分类集合
		List<CourseCategory> oneCategoryList = dao.listByCategoryTypeAndFloorAndStatusId(CategoryTypeEnum.COURSE.getCode(), 1, StatusIdEnum.YES.getCode());
		if (StringUtils.isEmpty(oneCategoryList)) {
			return Result.success(dto);
		}
		List<CourseCategoryDTO> oneList = new ArrayList<>();
		// 查找一级分类下的二级分类
		for (CourseCategory courseCategory : oneCategoryList) {
			// 设置一级分类
			CourseCategoryDTO oneCategory = BeanUtil.copyProperties(courseCategory, CourseCategoryDTO.class);

			// 查找一级分类下的二级分类
			List<CourseCategory> twoCategoryList = dao.listByParentId(courseCategory.getId());
			if (StringUtils.isEmpty(twoCategoryList)) {
				oneList.add(oneCategory);
				dto.setCourseCategoryList(oneList);
				continue;
			}
			List<CourseCategoryTwoDTO> twoList = new ArrayList<>();
			List<CourseCategoryThreeDTO> threeList = new ArrayList<>();
			for (CourseCategory twoCategory : twoCategoryList) {
				CourseCategoryTwoDTO twoDto = new CourseCategoryTwoDTO();
				twoDto = BeanUtil.copyProperties(twoCategory, CourseCategoryTwoDTO.class);
				List<CourseCategory> threeCategoryList = dao.listByParentId(twoCategory.getId());
				if (CollectionUtils.isNotEmpty(threeCategoryList)) {
					// 复制三级分类信息返回
					threeList = ArrayListUtil.copy(threeCategoryList, CourseCategoryThreeDTO.class);
					twoDto.setThreeList(threeList);
				}
				twoList.add(twoDto);
			}
			oneCategory.setTwoList(twoList);
			// 复制二级分类信息返回
			oneList.add(oneCategory);
			dto.setCourseCategoryList(oneList);
		}
		return Result.success(dto);
	}
	
}
