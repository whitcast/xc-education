package com.xc.education.user.service.controller.biz;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.aliyuncs.exceptions.ClientException;
import com.xc.education.system.common.bean.vo.SysVO;
import com.xc.education.system.common.interfaces.BossSys;
import com.xc.education.user.common.bean.qo.SendSmsLogQO;
import com.xc.education.user.common.bean.vo.SendSmsLogVO;
import com.xc.education.user.service.dao.PlatformDao;
import com.xc.education.user.service.dao.SendSmsLogDao;
import com.xc.education.user.service.dao.UserDao;
import com.xc.education.user.service.dao.impl.mapper.entity.Platform;
import com.xc.education.user.service.dao.impl.mapper.entity.SendSmsLog;
import com.xc.education.user.service.dao.impl.mapper.entity.SendSmsLogExample;
import com.xc.education.user.service.dao.impl.mapper.entity.SendSmsLogExample.Criteria;
import com.xc.education.user.service.dao.impl.mapper.entity.User;
import com.xc.education.util.aliyun.Aliyun;
import com.xc.education.util.aliyun.AliyunUtil;
import com.xc.education.util.base.BaseException;
import com.xc.education.util.base.Page;
import com.xc.education.util.base.PageUtil;
import com.xc.education.util.enums.IsSuccessEnum;
import com.xc.education.util.enums.StatusIdEnum;
import com.xc.education.util.tools.BeanUtil;
import com.xc.education.util.tools.DateUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.RandomUtil;

/**
 * 用户发送短信日志
 *
 * @author YZJ
 */
@Component
public class BossSendSmsLogBiz {

	private static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199)\\d{8}$";// 手机号码校验

	@Autowired
	private SendSmsLogDao dao;
	@Autowired
	private PlatformDao platformDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private BossSys bossSys;

	public Page<SendSmsLogVO> listForPage(SendSmsLogQO qo) {
		SendSmsLogExample example = new SendSmsLogExample();
		Criteria c = example.createCriteria();
		if (StringUtils.hasText(qo.getMobile())) {
			c.andMobileEqualTo(qo.getMobile());
		}
		if (StringUtils.hasText(qo.getBeginGmtCreate())) {
			c.andGmtCreateGreaterThanOrEqualTo(DateUtil.parseDate(qo.getBeginGmtCreate(), "yyyy-MM-dd"));
		}
		if (StringUtils.hasText(qo.getEndGmtCreate())) {
			c.andGmtCreateLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(qo.getEndGmtCreate(), "yyyy-MM-dd"), 1));
		}
		example.setOrderByClause(" id desc ");
		Page<SendSmsLog> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, SendSmsLogVO.class);
	}

	public int save(SendSmsLogQO qo) {
		SendSmsLog record = BeanUtil.copyProperties(qo, SendSmsLog.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public SendSmsLogVO getById(Long id) {
		SendSmsLog record = dao.getById(id);
		return BeanUtil.copyProperties(record, SendSmsLogVO.class);
	}

	public int updateById(SendSmsLogQO qo) {
		SendSmsLog record = BeanUtil.copyProperties(qo, SendSmsLog.class);
		return dao.updateById(record);
	}

	public int send(SendSmsLogQO qo) {
		if (StringUtils.isEmpty(qo.getMobile())) {
			throw new BaseException("手机号不能为空");
		}
		// 手机号码校验
		if (!Pattern.compile(REGEX_MOBILE).matcher(qo.getMobile()).matches()) {
			throw new BaseException("手机号码格式不正确");
		}
		User user = userDao.getByMobile(qo.getMobile());
		if (ObjectUtil.isNull(user)) {
			throw new BaseException("用户不存在");
		}
		Platform platform = platformDao.getByClientId(user.getUserSource());
		if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
			throw new BaseException("平台状态异常");
		}

		if (redisTemplate.hasKey(platform.getClientId() + qo.getMobile())) {
			throw new BaseException("操作过于频繁，请稍后重试（不少于5分钟）");
		}
		SysVO sys = bossSys.getSys();
		if (ObjectUtil.isNull(sys)) {
			throw new BaseException("未配置系统配置表");
		}
		if (StringUtils.isEmpty(sys.getAliyunAccessKeyId()) || StringUtils.isEmpty(sys.getAliyunAccessKeySecret())) {
			throw new BaseException("aliyunAccessKeyId或aliyunAccessKeySecret未配置");
		}
		if (StringUtils.isEmpty(sys.getSmsCode()) || StringUtils.isEmpty(sys.getSignName())) {
			throw new BaseException("smsCode或signName未配置");
		}
		// 创建日志实例
		SendSmsLog sendSmsLog = new SendSmsLog();
		sendSmsLog.setMobile(qo.getMobile());
		sendSmsLog.setTemplate(sys.getSmsCode());
		// 随机生成验证码
		sendSmsLog.setSmsCode(RandomUtil.randomNumbers(6));
		try {
			// 发送验证码
			boolean result = AliyunUtil.sendMsg(qo.getMobile(), sendSmsLog.getSmsCode(), BeanUtil.copyProperties(sys, Aliyun.class));
			if (result) {
				// 成功发送，验证码存入缓存：5分钟有效
				redisTemplate.opsForValue().set(platform.getClientId() + qo.getMobile(), sendSmsLog.getSmsCode(), 5, TimeUnit.MINUTES);
				sendSmsLog.setIsSuccess(IsSuccessEnum.SUCCESS.getCode());
				return dao.save(sendSmsLog);
			}
			// 发送失败
			sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
			dao.save(sendSmsLog);
			throw new BaseException("发送失败");
		} catch (ClientException e) {
			sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
			dao.save(sendSmsLog);
			throw new BaseException("发送失败，原因={" + e.getErrMsg() + "}");
		}
	}

}
