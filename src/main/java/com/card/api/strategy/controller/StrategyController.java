package com.card.api.strategy.controller;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	 * 查询动态列表
	 * @param id 动态主键
	 * @param hot 热度
	 * @param size 分页属性
	 * @param page 分页属性
	 * @param time 时间戳
	 * @param api_key apikey
	 * @param sign 签名
	 * @return
	 */
	@ApiOperation(value = "查询动态列表", notes = "可根据主键，查询条数查询出对应的数据")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "id",value = "根据主键查询动态[不为空时参与签名]",dataType = "Long"),
			@ApiImplicitParam(name = "hot",value = "热度查询,1根据热度倒序[不为空时参与签名]",dataType = "Integer"),
			@ApiImplicitParam(name = "size",value = "分页属性：一页数据条数[默认20]",dataType = "Integer"),
			@ApiImplicitParam(name = "page",value = "分页属性：当前页码[默认1]",dataType = "Integer"),
			@ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String", required = true),
			@ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
			@ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String", required = true)
	})
	@RequestMapping(value = "/{time}/{api_key}/{sign}", method = { RequestMethod.GET })
	public JSONObject list(@RequestParam(value = "id",defaultValue = "") Long id,
						   @RequestParam(value = "hot",defaultValue = "") Integer hot,
						   @RequestParam(value = "size",defaultValue = "20") Integer size ,
						   @RequestParam(value = "page",defaultValue = "1") Integer page ,
						   @PathVariable String time,
						   @PathVariable String api_key,
						   @PathVariable String sign)
	{
		returnJson.clear();
		// 标识，默认为true
		boolean flag = true;
		try {
			// 验证接口是否有权访问
			if (SecurityUtils.validate(new HashMap<String,Object>() {
				/**
				 * 序列号
				 */
				private static final long serialVersionUID = 2138548644834576384L;
				{
					put("id",id);
					put("hot",hot);
					put(SecurityUtils._TIME, time);
					put(SecurityUtils._API_KEY, api_key);
					put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
					put(SecurityUtils._SIGN, sign);
				}
			}))
			{
				// 执行发送短信
				List<StrategyBean> strategys = strategyService.list(id,size,page,hot);
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
