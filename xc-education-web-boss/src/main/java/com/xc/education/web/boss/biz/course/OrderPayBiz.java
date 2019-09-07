package com.xc.education.web.boss.biz.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xc.education.course.common.bean.qo.OrderPayQO;
import com.xc.education.course.common.bean.vo.OrderPayVO;
import com.xc.education.course.feign.IBossOrderPay;
import com.xc.education.util.base.BaseBiz;
import com.xc.education.util.base.Page;

/**
 * 订单支付信息表 
 *
 * @author wujing
 */
@Component
public class OrderPayBiz extends BaseBiz{

	@Autowired
	private IBossOrderPay bossOrderPay;

	public Page<OrderPayVO> listForPage(OrderPayQO qo) {
        return bossOrderPay.listForPage(qo);
	}

	public int save(OrderPayQO qo) {
		return bossOrderPay.save(qo);
	}

	public int deleteById(Long id) {
		return bossOrderPay.deleteById(id);
	}

	public OrderPayVO getById(Long id) {
		return bossOrderPay.getById(id);
	}

	public int updateById(OrderPayQO qo) {
		return bossOrderPay.updateById(qo);
	}
	
}
