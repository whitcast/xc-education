package com.xc.education.web.boss.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xc.education.user.common.bean.vo.LecturerVO;
import com.xc.education.user.feign.IBossLecturer;
import com.xc.education.util.base.Base;

public class BizBoss extends Base {

	@Autowired
	private IBossLecturer bossLecturer;

	/**
	 * 列出所有的讲师
	 * 
	 * @author LHR
	 * @return
	 */
	public List<LecturerVO> listAllLecturer() {
		return bossLecturer.listAllForLecturer();
	}

}
