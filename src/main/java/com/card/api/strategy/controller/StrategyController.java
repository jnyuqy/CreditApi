package com.card.api.strategy.controller;

import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.card.api.strategy.bean.StrategyBean;
import com.card.api.strategy.service.StrategyService;
import com.card.api.utils.SecurityUtils;
import com.card.core.constants.SysMsgConstants;
import com.card.core.controller.BaseController;
import com.card.core.exception.LogicException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 动态查询<br>
 * 最新动态，最热动态查询<br>
 * 
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：StrategyController <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月6日 上午11:47:13 <br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月6日 上午11:47:13 <br>
 * 修改备注：
 */
@RestController
@RequestMapping(value = "strategy/")
public class StrategyController extends BaseController{

	@Autowired
	private StrategyService strategyService;
	/**  
	* 序列号
	* {@value}
	*/ 
	private static final long serialVersionUID = 8861021575147921274L;
	
	/**
	 * 查询动态列表<br>
	 * 2017年1月6日 上午11:47:10<br>
	 * list
	 * 
	 * @param time
	 *            时间戳<br>
	 * @param api_key
	 *            apikey<br>
	 * @param sign
	 *            签名<br>
	 * @param strategy
	 *            查询实体<br>
	 * @return
	 * @return JSONObject
	 */
	@ApiOperation(value = "查询动态列表", notes = "可根据主键，查询条数查询出对应的数据")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "strategy", value = "查询实体,size : 查询条数,id : 动态主键,hot : 1 => 最新动态", dataType = "BankBean"),
			@ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String", required = true),
			@ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
			@ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String", required = true) })
	@RequestMapping(value = "/{time}/{api_key}/{sign}", method = { RequestMethod.POST })
	public JSONObject list(@PathVariable String time,@PathVariable String api_key,@PathVariable String sign,@RequestBody StrategyBean strategy){
		returnJson.clear();
		// 标识，默认为true
		boolean flag = true;
		try {
			// 验证接口是否有权访问
			if (SecurityUtils.validate(new Hashtable<String, Object>() {
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
				List<StrategyBean> strategys = strategyService.list(strategy); 
				//设置返回验证码
				returnJson.put(RETURN_RESULT, strategys);
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
