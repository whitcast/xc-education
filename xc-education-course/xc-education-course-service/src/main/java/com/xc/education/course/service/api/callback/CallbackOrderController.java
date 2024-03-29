/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.xc.education.course.service.api.callback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xc.education.course.service.biz.callback.CallbackOrderBiz;
import com.xc.education.course.service.common.bo.OrderInfoPayNotifyBO;
import com.xc.education.course.service.common.bo.callback.CallbackOrderBO;
import com.xc.education.system.common.bean.vo.SysVO;
import com.xc.education.system.feign.IBossSys;
import com.xc.education.util.base.BaseController;
import com.xc.education.util.enums.OrderStatusEnum;
import com.xc.education.util.enums.TradeStatusEnum;
import com.xc.education.util.pay.MerchantApiUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 订单信息表
 *
 * @author wujing123
 */
@RestController
public class CallbackOrderController extends BaseController {

	@Autowired
	private CallbackOrderBiz biz;

	@Autowired
	private IBossSys bossSys;

	/**
	 * 龙果支付回调
	 * 
	 * @param CallbackOrderBO
	 * @return
	 */
	@ApiOperation(value = "龙果支付回调接口", notes = "龙果支付回调接口")
	@RequestMapping(value = "/callback/order/xc", method = { RequestMethod.POST, RequestMethod.GET })
	public String xcPayNotify(@ModelAttribute CallbackOrderBO callbackOrderBO) {
		logger.info("龙果支付回调结果：{}", callbackOrderBO);
		// sign校验
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("payKey", callbackOrderBO.getPayKey());
		paramMap.put("productName", callbackOrderBO.getProductName());
		paramMap.put("outTradeNo", callbackOrderBO.getOutTradeNo());
		paramMap.put("orderPrice", callbackOrderBO.getOrderPrice());
		paramMap.put("productType", callbackOrderBO.getProductType());
		paramMap.put("tradeStatus", callbackOrderBO.getTradeStatus());
		paramMap.put("successTime", callbackOrderBO.getSuccessTime());// 商品名称
		paramMap.put("orderTime", callbackOrderBO.getOrderTime());
		paramMap.put("trxNo", callbackOrderBO.getTrxNo());
		paramMap.put("remark", callbackOrderBO.getRemark());

		SysVO sys = bossSys.getSys();
		if (ObjectUtil.isNull(sys)) {
			return "找不到系统配置信息";
		}
		if (StringUtils.isEmpty(sys.getPaySecret())) {
			return "paySecret未配置";
		}

		// 注意：这里返回的备注信息=机构号
		// 根据机构编号查找机构的扩展信息
		String sign = MerchantApiUtil.getSign(paramMap, sys.getPaySecret());
		if (!sign.equals(callbackOrderBO.getSign())) {
			logger.error("签名前参数={}，签名sign={}，签名secret={}", paramMap, sign, sys.getPaySecret());
			return "fail";
		}

		// 转换支付回调返回的实体数据
		OrderInfoPayNotifyBO payNotifyBO = new OrderInfoPayNotifyBO();
		// 注意：这里返回的交易号=订单的流水号
		payNotifyBO.setSerialNumber(Long.valueOf(callbackOrderBO.getOutTradeNo()));
		// 状态转换
		if (TradeStatusEnum.SUCCESS.getCode().equals(callbackOrderBO.getTradeStatus())) {
			payNotifyBO.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
		} else if (TradeStatusEnum.FAILED.getCode().equals(callbackOrderBO.getTradeStatus())) {
			payNotifyBO.setOrderStatus(OrderStatusEnum.FAIL.getCode());
		}

		return biz.payNotify(payNotifyBO).getData();
	}
}
