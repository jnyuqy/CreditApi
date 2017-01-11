package com.card.api.remind.controller;

import java.util.HashMap;
import java.util.List;

import com.card.api.bank.bean.BankBean;
import com.card.core.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.card.api.remind.bean.RemindBean;
import com.card.api.remind.service.RemindService;
import com.card.api.utils.SecurityUtils;
import com.card.core.constants.SysMsgConstants;
import com.card.core.controller.BaseController;
import com.card.core.exception.LogicException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 还款提醒控接口<br>
 * 
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：RemindController <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月6日 下午5:37:17 <br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月6日 下午5:37:17 <br>
 * 修改备注：
 */
@RestController
@RequestMapping(value = "remind/")
public class RemindController extends BaseController{
	
	@Autowired
	private RemindService remindService;
	/**  
	* 序列号
	* {@value}
	*/ 
	private static final long serialVersionUID = -2372234121163092589L;
	
	/**
	 * 添加还款提醒<br>
	 * 2017年1月6日 下午5:58:26<br>
	 * login<br>
	 * 
	 * @param phone
	 *            手机号码<br>
	 * @param time
	 *            时间戳<br>
	 * @param api_key
	 *            api_key<br>
	 * @param sign
	 *            签名<br>
	 *            还款提醒实体<br>
	 * @return<br>
	 * @return JSONObject
	 */
	@ApiOperation(value = "添加还款提醒", notes = "根据还款提醒实体保存数据")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "bankId", value = "手机号码", dataType = "Long",required = true),
			@ApiImplicitParam(name = "openPhoneMsg", value = "开通手机短信提醒,0：不开启,1：开启", dataType = "Integer"),
			@ApiImplicitParam(name = "openPhoneMsgDays", value = "手机短信提醒提前天数", dataType = "Integer"),
			@ApiImplicitParam(name = "openSystemMsg", value = "开通系统消息提醒,0：不开启,1：开启", dataType = "Integer"),
			@ApiImplicitParam(name = "openSystemMsgDays", value = "系统消息提示提前天数", dataType = "Integer"),
			@ApiImplicitParam(name = "phone", value = "手机号码", dataType = "String",required = true),
			@ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String",required = true),
			@ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String",required = true),
			@ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String",required = true)
	})
	@RequestMapping(value = "/save/{phone}/{time}/{api_key}/{sign}", method = RequestMethod.POST)
	public JSONObject save
		(
				@PathVariable String phone,
				@PathVariable String time,
				@PathVariable String api_key,
				@PathVariable String sign,
				@RequestParam (value = "bankId",required = true) Long bankId,
				@RequestParam(value = "openPhoneMsg",defaultValue = "") Integer openPhoneMsg,
				@RequestParam(value = "openPhoneMsgDays",defaultValue = "") Integer openPhoneMsgDays,
				@RequestParam(value = "openSystemMsg",defaultValue = "") Integer openSystemMsg,
				@RequestParam(value = "openSystemMsgDays",defaultValue = "") Integer openSystemMsgDays

		)
	{
		//标识，默认为true
		boolean flag = true;
		//清空集合
		returnJson.clear();
		try {
			//验证接口是否有权访问
			if (SecurityUtils.validate(new HashMap<String, Object>() {
				/**
				 * 序列号
				 */
				private static final long serialVersionUID = 2138548644834576384L;
				{
					put("bankId", bankId);
					put("openPhoneMsg", openPhoneMsg);
					put("openPhoneMsgDays", openPhoneMsgDays);
					put("openSystemMsg", openSystemMsg);
					put("openSystemMsgDays", openSystemMsgDays);
					put("phone", phone);
					put(SecurityUtils._TIME, time);
					put(SecurityUtils._API_KEY, api_key);
					put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
					put(SecurityUtils._SIGN, sign);
				}
			})) 
			{
				//收集参数
				RemindBean remind = new RemindBean();
				remind.setBank(new BankBean(bankId));
				remind.setPhoneMess(ValidatorUtils.isEmpty(openPhoneMsg) ? 0 :openPhoneMsg);
				remind.setPhoneMessDay(ValidatorUtils.isEmpty(openPhoneMsgDays) ? 0 :openPhoneMsgDays);
				remind.setSystemMess(ValidatorUtils.isEmpty(openSystemMsg) ? 0 :openSystemMsg);
				remind.setSystemMessDay(ValidatorUtils.isEmpty(openSystemMsgDays) ? 0 :openSystemMsgDays);

				//保存还款提醒
				remindService.save(phone, remind);
			}
		}
		//业务异常
		catch (LogicException e) {
			e.printStackTrace();
			flag = false;
			//设置返回错误消息
			returnJson.put(RETURN_MSG, msgUtils.getValue(e.getMsgId(), e.getParams()));
		}
		//系统异常
		catch (Exception e) {
			e.printStackTrace();
			flag = false;
			returnJson.put(RETURN_MSG, msgUtils.getValue(SysMsgConstants.SYSTEM_ERROR));
		}
		//返回执行标识
		returnJson.put(RETURN_FLAG, flag);
		return returnJson;
	}

	/**
	 * 查询还款提醒列表<br>
	 * 2017年1月6日 下午6:31:45<br>
	 * list<br>
	 * 
	 * @param time
	 *            时间戳<br>
	 * @param api_key
	 *            apikey<br>
	 * @param sign
	 *            签名<br>
	 *            查询实体<br>
	 * @return<br>
	 * @return JSONObject
	 */
	@ApiOperation(value = "还款提醒查询", notes = "根据还款提醒实体查询列表")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "phone", value = "手机号码", dataType = "String",required = true),
			@ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String",required = true),
			@ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String",required = true),
			@ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String",required = true)
	})
	@RequestMapping(value = "/{phone}/{time}/{api_key}/{sign}", method = RequestMethod.GET)
	public JSONObject list
		(
				@PathVariable String phone,
				@PathVariable String time,
				@PathVariable String api_key,
				@PathVariable String sign
		)
	{
		//标识，默认为true
		boolean flag = true;
		//清空集合
		returnJson.clear();
		try {
			//验证接口是否有权访问
			if (SecurityUtils.validate(new HashMap<String, Object>() {
				/**
				 * 序列号
				 */
				private static final long serialVersionUID = 2138548644834576384L;
				{
					put("phone", phone);
					put(SecurityUtils._TIME, time);
					put(SecurityUtils._API_KEY, api_key);
					put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
					put(SecurityUtils._SIGN, sign);
				}
			})) 
			{
				//返回还款提醒列表
				List<RemindBean> reminds = remindService.list(phone);
				returnJson.put(RETURN_RESULT, reminds);
			}
		}
		//业务异常
		catch (LogicException e) {
			e.printStackTrace();
			flag = false;
			//设置返回错误消息
			returnJson.put(RETURN_MSG, msgUtils.getValue(e.getMsgId(), e.getParams()));
		}
		//系统异常
		catch (Exception e) {
			e.printStackTrace();
			flag = false;
			returnJson.put(RETURN_MSG, msgUtils.getValue(SysMsgConstants.SYSTEM_ERROR));
		}
		//返回执行标识
		returnJson.put(RETURN_FLAG, flag);
		return returnJson;
	}
}
