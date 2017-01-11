package com.card.api.bank.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.card.api.bank.bean.BankBean;
import com.card.api.bank.bean.BankServiceBean;
import com.card.api.bank.service.BankService;
import com.card.api.bank.service.BankServiceService;
import com.card.api.utils.SecurityUtils;
import com.card.core.constants.SysMsgConstants;
import com.card.core.controller.BaseController;
import com.card.core.exception.LogicException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 银行api接口控制器<br>
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：BankController<br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月5日 下午3:59:51<br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月5日 下午3:59:51<br>
 * 修改备注：
 */
@RestController
@RequestMapping(value = "bank/")
public class BankController extends BaseController{

	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private BankServiceService bankServiceService;
	/**  
	* 序列号
	* {@value}
	*/ 
	private static final long serialVersionUID = -9183003573707425784L;
	
	/**
	 * 查询银行列表<br>
	 * 2017年1月5日 下午4:21:01<br>
	 * list<br>
	 * 
	 * @param time
	 *            时间戳<br>
	 * @param api_key
	 *            apikey<br>
	 * @param sign
	 *            签名<br>
	 * @param bank
	 *            银行实体<br>
	 * @return
	 * @return JSONObject
	 */
	@ApiOperation(value = "查询银行列表", notes = "可根据条件查询热门银行，条数")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "bank", value = "查询实体,size : 查询条数,name : 银行名称,hot : 热门(1热门银行，0非热门)", dataType = "BankBean"),
			@ApiImplicitParam(name = "time", value = "时间戳,如：1321234992963601", dataType = "String", required = true),
			@ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
			@ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String", required = true) })
	@RequestMapping(value = "/{time}/{api_key}/{sign}", method = { RequestMethod.POST })
	public JSONObject list(@PathVariable String time, @PathVariable String api_key, @PathVariable String sign,
			@RequestBody BankBean bank) {
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
					put(SecurityUtils._TIME, time);
					put(SecurityUtils._API_KEY, api_key);
					put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
					put(SecurityUtils._SIGN, sign);
				}
			})) {
				// 执行发送短信
				List<BankBean> banks = bankService.list(bank);
				//设置返回验证码
				returnJson.put(RETURN_RESULT, banks);
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
	
	/**
	 * 根据银行编号查询银行服务<br>
	 * 2017年1月6日 下午4:03:58<br>
	 * service<br>
	 * 
	 * @param time
	 *            时间戳<br>
	 * @param api_key
	 *            apikey<br>
	 * @param sign
	 *            签名<br>
	 * @param bankService
	 *            银行服务实体<br>
	 * @return<br>
	 * @return JSONObject
	 */
	@ApiOperation(value = "查询银行服务列表", notes = "根据银行编号查询银行服务列表")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "bankService", value = "查询实体,bank.id => 银行编号", dataType = "BankServiceBean"),
			@ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String", required = true),
			@ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
			@ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String", required = true) })
	@RequestMapping(value = "/service/{time}/{api_key}/{sign}", method = { RequestMethod.POST })
	public JSONObject service(@PathVariable String time, @PathVariable String api_key, @PathVariable String sign,
			@RequestBody BankServiceBean bankService) {
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
					put(SecurityUtils._TIME, time);
					put(SecurityUtils._API_KEY, api_key);
					put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
					put(SecurityUtils._SIGN, sign);
				}
			})) {
				// 执行查询银行服务
				List<BankServiceBean> services = bankServiceService.list(bankService);
				//设置返回服务列表
				returnJson.put(RETURN_RESULT, services);
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
