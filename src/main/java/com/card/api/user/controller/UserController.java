package com.card.api.user.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.card.api.user.bean.UserBean;
import com.card.api.user.service.UserService;
import com.card.api.utils.SecurityUtils;
import com.card.core.constants.SysMsgConstants;
import com.card.core.controller.BaseController;
import com.card.core.exception.LogicException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户控制器<br>
 * 包含了用户登录<br>
 * 用户信息维护<br>
 * 
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：UserController <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月3日 上午11:28:01 <br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月3日 上午11:28:01 <br>
 * 修改备注：<br>
 */
@RestController
@RequestMapping(value = "user/")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	/**  
	* 序列号
	* {@value}
	*/ 
	private static final long serialVersionUID = 6365554173666819182L;

	/**
	 * 登录接口 <br>
	 * 2017年1月3日 上午11:53:46<br>
	 * login<br>
	 * 
	 * @param name
	 *            登录名<br>
	 * @param pwd
	 *            密码<br>
	 * @param time
	 *            时间戳<br>
	 * @param api_key
	 *            客户端api_key<br>
	 * @param sign
	 *            签名<br>
	 * @return Object 返回登录信息或者异常消息
	 */
	@ApiOperation(value = "用户登录", notes = "根据用户名以及密码获取用户实体对象")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "name", value = "用户名", dataType = "String",required = true),
			@ApiImplicitParam(name = "pwd", value = "密码", dataType = "String",required = true),
			@ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String",required = true),
			@ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String",required = true),
			@ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String",required = true)
	})
	@RequestMapping(value = "/login/{name}/{pwd}/{time}/{api_key}/{sign}", method = RequestMethod.GET)
	public JSONObject login
		(
				@PathVariable String name,
				@PathVariable String pwd,
				@PathVariable String time,
				@PathVariable String api_key,
				@PathVariable String sign
		)
	{
		//登录对象
		UserBean user = new UserBean();
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
					put("name", name);
					put("pwd", pwd);
					put(SecurityUtils._TIME, time);
					put(SecurityUtils._API_KEY, api_key);
					put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
					put(SecurityUtils._SIGN, sign);
				}
			})) 
			{
				// 收集参数
				user.setName(name);
				user.setPwd(pwd);
				// 执行登录业务
				user = userService.login(user);
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
		//如果登录成功返回对象
		if(flag)
			returnJson.put(RETURN_RESULT, user);
		return returnJson;
	}
	
	/**
	 * 用户注册<br>
	 * 2017年1月4日 下午3:36:42<br>
	 * register<br>
	 * 
	 * @param phone
	 *            注册手机号码<br>
	 * @param time
	 *            时间戳<br>
	 * @param api_key
	 *            apikey<br>
	 * @param sign
	 *            签名<br>
	 * @return<br>
	 * @return JSONObject
	 */
	@ApiOperation(value = "用户注册", notes = "根据手机号码注册用户")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "phone", value = "手机号码", dataType = "String", required = true),
			@ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String", required = true),
			@ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
			@ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String", required = true) })
	@RequestMapping(value = "/register/{phone}/{time}/{api_key}/{sign}", method = RequestMethod.POST)
	public JSONObject register
		(
				@PathVariable String phone,
				@PathVariable String time,
				@PathVariable String api_key,
			    @PathVariable String sign
		)
	{
		//登录对象
		UserBean user = new UserBean();
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
				// 收集参数
				user.setPhone(phone);
				// 执行注册业务
				user = userService.register(user);
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
		//如果登录成功返回对象
		if(flag)
			returnJson.put(RETURN_RESULT, user);
		return returnJson;
	}

	/**
	 * 修改用户密码<br>
	 * 2017年1月4日 下午3:44:59<br>
	 * modifyPwd<br>
	 *
	 *            新密码<br>
	 * @param time
	 *            时间戳<br>
	 * @param api_key
	 *            apikey<br>
	 * @param sign
	 *            签名<br>
	 * @return<br>
	 * @return JSONObject
	 */
	@ApiOperation(value = "修改用户密码", notes = "修改用户密码")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "phone", value = "手机号码", dataType = "String", required = true),
			@ApiImplicitParam(name = "pwd", value = "新密码", dataType = "String", required = true),
			@ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String", required = true),
			@ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
			@ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String", required = true) })
	@RequestMapping(value = "/modifyPwd/{phone}/{time}/{api_key}/{sign}", method = RequestMethod.POST)
	public JSONObject modifyPwd
		(
				@PathVariable String time,
				@PathVariable String api_key,
				@PathVariable String sign,
				@PathVariable String phone,
				@RequestParam(value = "pwd",required = true) String pwd
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
					put("pwd", pwd);
					put(SecurityUtils._TIME, time);
					put(SecurityUtils._API_KEY, api_key);
					put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
					put(SecurityUtils._SIGN, sign);
				}
			})) 
			{
				UserBean user = new UserBean();
				user.setPhone(phone);
				user.setPwd(pwd);

				// 执行修改密码
				userService.modifyPwd(user);
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
	 * 修改用户昵称<br>
	 * 2017年1月5日 上午11:36:11<br>
	 * modifyNickName<br>
	 * 
	 * @param time
	 *            时间戳<br>
	 * @param api_key
	 *            api_key<br>
	 * @param sign
	 *            签名<br>
	 * @return<br>
	 * @return JSONObject 返回对象
	 */
	@ApiOperation(value = "修改用户昵称", notes = "修改用户昵称")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "phone", value = "手机号码", dataType = "String", required = true),
			@ApiImplicitParam(name = "nickName", value = "新昵称", dataType = "String", required = true),
			@ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String", required = true),
			@ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
			@ApiImplicitParam(name = "sign", value = "签名,(参数列表+time+api_key)生成url串首字母排序正序=sign", dataType = "String", required = true) })
	@RequestMapping(value = "/modifyNickName/{phone}/{time}/{api_key}/{sign}", method = RequestMethod.POST)
	public JSONObject modifyNickName
		(
			@PathVariable String time,
			@PathVariable String api_key,
			@PathVariable String sign,
			@PathVariable String phone,
			@RequestParam(value = "nickName",required = true) String nickName
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
					put("nickName", nickName);
					put(SecurityUtils._TIME, time);
					put(SecurityUtils._API_KEY, api_key);
					put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
					put(SecurityUtils._SIGN, sign);
				}
			})) 
			{
				UserBean user = new UserBean();
				user.setPhone(phone);
				user.setNickName(nickName);

				// 执行修改昵称
				userService.modifyNickName(user);
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
	 * 修改用户邮箱<br>
	 * 2017年1月5日 上午11:52:22<br>
	 * modifyMail<br>
	 * 
	 * @param time
	 *            时间戳<br>
	 * @param api_key
	 *            api_key<br>
	 * @param sign
	 *            签名<br>
	 *            用户对象,requestBody<br>
	 * @return<br>
	 * @return JSONObject
	 */
	@ApiOperation(value = "修改用户邮箱", notes = "修改用户邮箱")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "phone", value = "手机号码", dataType = "String", required = true),
			@ApiImplicitParam(name = "mail", value = "邮箱", dataType = "String", required = true),
			@ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String", required = true),
			@ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
			@ApiImplicitParam(name = "sign", value = "签名,(参数列表+time+api_key)生成url串首字母排序正序=sign", dataType = "String", required = true) })
	@RequestMapping(value = "/modifyMail/{phone}/{time}/{api_key}/{sign}", method = RequestMethod.POST)
	public JSONObject modifyMail
		(
			@PathVariable String time,
			@PathVariable String api_key,
			@PathVariable String sign,
			@PathVariable String phone,
			@RequestParam(value = "mail",required = true) String mail
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
					put("mail", mail);
					put(SecurityUtils._TIME, time);
					put(SecurityUtils._API_KEY, api_key);
					put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
					put(SecurityUtils._SIGN, sign);
				}
			})) 
			{
				UserBean user = new UserBean();
				user.setPhone(phone);
				user.setMail(mail);

				// 执行修改邮箱
				userService.modifyMail(user);
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
	 * 上传头像<br>
	 * 2017年1月5日 下午2:05:50<br>
	 * uploadHeadImg
	 * 
	 * @param phone
	 *            手机号码<br>
	 * @param time
	 *            时间戳<br>
	 * @param api_key
	 *            api_key<br>
	 * @param sign
	 *            签名<br>
	 * @param file
	 *            头像图片<br>
	 * @return<br>
	 * @return JSONObject
	 */
	@ApiOperation(value = "上传头像", notes = "可应用上传头像，图片等")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "phone", value = "手机号码", dataType = "String", required = true),
			@ApiImplicitParam(name = "file", value = "上传文件对象", dataType = "File", required = true),
			@ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String", required = true),
			@ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
			@ApiImplicitParam(name = "sign", value = "签名,(参数列表+time+api_key)生成url串首字母排序正序=sign", dataType = "String", required = true) })
	@RequestMapping(value = "/uploadHeadImg/{phone}/{time}/{api_key}/{sign}", consumes = "multipart/form-data", method = RequestMethod.POST)
	public JSONObject uploadHeadImg
		(
				@PathVariable String phone,
				@PathVariable String time,
				@PathVariable String api_key,
				@PathVariable String sign,
				@RequestParam("file") CommonsMultipartFile file) {
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
				// 执行上传图片
				userService.uploadHeadImg(phone, file);
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
