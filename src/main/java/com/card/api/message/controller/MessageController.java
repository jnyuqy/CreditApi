package com.card.api.message.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.card.api.message.service.MessageService;
import com.card.api.utils.SecurityUtils;
import com.card.core.constants.SysMsgConstants;
import com.card.core.controller.BaseController;
import com.card.core.exception.LogicException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 短信验证码控制器 <br>
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：MessageController<br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月4日 上午11:54:35<br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月4日 上午11:54:35<br>
 * 修改备注：<br>
 */
@RestController
@RequestMapping("message/")
public class MessageController extends BaseController {

	/**
	 * 序列号 {@value}
	 */
	private static final long serialVersionUID = -5450033147915991330L;

	//短信业务
	@Autowired
	private MessageService messageService;

	/**
	 * 发送短信验证码 <br>
	 * 2017年1月4日 下午12:02:59 send
	 * 
	 * @param phone
	 *            手机号码<br>
	 * @param time
	 *            时间戳<br>
	 * @param api_key
	 *            apiKey<br>
	 * @param sign
	 *            签名<br>
	 * @return<br>
	 * @return JSONObject
	 */
	@ApiOperation(value = "发送短信验证码", notes = "用户登录、注册时发送验证码")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "phone", value = "接受者手机号码", dataType = "String", required = true),
			@ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String", required = true),
			@ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
			@ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String", required = true) })
	@RequestMapping(value = "/send/{phone}/{time}/{api_key}/{sign}", method = { RequestMethod.POST })
	public JSONObject send(@PathVariable String phone, @PathVariable String time, @PathVariable String api_key,
			@PathVariable String sign) {
		returnJson.clear();
		// 标识，默认为true
		boolean flag = true;
		try {
			// 验证接口是否有权访问
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
			})) {
				// 执行发送短信
				String code = messageService.sendMessage(phone);
				//设置返回验证码
				returnJson.put(RETURN_RESULT, code);
			}
		} // 业务异常
		catch (LogicException e) {
			e.printStackTrace();
			flag = false;
			// 设置返回错误消息
			returnJson.put(RETURN_MSG, msgUtils.getValue(e.getMsgId(), e.getParams()));
		}
		// 系统异常
		catch (Exception e) {
			e.printStackTrace();
			flag = false;
			returnJson.put(RETURN_MSG, msgUtils.getValue(SysMsgConstants.SYSTEM_ERROR));
		}
		// 返回执行标识
		returnJson.put(RETURN_FLAG, flag);
		return returnJson;
	}
}
