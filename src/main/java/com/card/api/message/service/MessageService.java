package com.card.api.message.service;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.card.core.constants.SysMsgConstants;
import com.card.core.exception.LogicException;
import com.card.core.utils.JSONUtils;
import com.card.core.utils.ParseUtils;
import com.card.core.utils.ValidatorUtils;

/**
 * 短信业务逻辑 <br>
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：MessageService<br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月4日 下午12:05:11<br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月4日 下午12:05:11<br>
 * 修改备注：
 */
@Service
public class MessageService implements Serializable{
	
	//临时存放短信验证码，key : 手机号码,value : string[],index => 0(短信编号),index => 1(验证码),index => 2(时间戳)
	private static final Hashtable<String, String[]> CODES = new Hashtable<String, String[]>();
	//短信接口路径
	private static final String MSG_URL = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	//短信用户名
	private static final String MSG_USER = "cf_suxin";
	//短信apikey
	private static final String MSG_API_KEY = "30affa5692c8974113f6813d68a8aa6f";
	/**  
	* 序列号
	* {@value}
	*/ 
	private static final long serialVersionUID = 6781587151409811176L;
	
	/**
	 * 发送短信验证码<br>
	 * 2017年1月4日 下午12:05:01<br>
	 * sendMessage<br>
	 * 
	 * @param phone 接受者手机号码<br>
	 * @return<br>
	 * @throws LogicException<br>
	 * @return String
	 */
	public String sendMessage(String phone) throws LogicException
	{
		//验证参数传入是否正确
		if(ValidatorUtils.isEmpty(phone) || !ValidatorUtils.isMobile(phone))
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "手机号码");
		}
		
		//获取内存中的验证码
		String memory_code = get_memory_code(phone);
		//如果存在直接返回验证码
		if(!ValidatorUtils.isEmpty(memory_code)){ return memory_code; }
		
		//创建httpclient对象
		HttpClient client = new HttpClient();
		//构建post请求方法对象
		PostMethod method = new PostMethod(MSG_URL);
		//设置发送参数的字符集
		client.getParams().setContentCharset("GBK");
		//设置方法请求头
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");
		//短信验证码
		String mobile_code = ParseUtils.toString((int) ((Math.random() * 9 + 1) * 100000));
		//短信内容
	    String content = new String("您的验证码是：" + mobile_code + ",该验证码30分钟内有效!");
	    //参数列表
		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", MSG_USER), 
			    new NameValuePair("password", MSG_API_KEY),
			    new NameValuePair("mobile", phone),
			    new NameValuePair("content", content),
		};
		//设置请求内容
		method.setRequestBody(data);
		try {
			//执行post方法
			client.executeMethod(method);
			//获取请求返回的body
			String SubmitResult = method.getResponseBodyAsString();
			//解析xml
			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();
			//获取code标识
			String code = root.elementText("code");
			//短信平台返回内容
			String msg = root.elementText("msg");
			//短信编号，唯一标识
			String smsid = root.elementText("smsid");
			//如果发送失败
			if(!"2".equals(code)){
				throw new LogicException(SysMsgConstants.XX_ERROR, msg, "短信发送");
			}
			//将短信验证码添加到临时集合内
			CODES.put(phone, new String[] { smsid, mobile_code, ParseUtils.toString(System.nanoTime()) });
		}
		//业务逻辑异常
		catch (LogicException e) {
			throw e;
		}
		//系统异常
		catch (Exception e) {
			e.printStackTrace();
			throw new LogicException(SysMsgConstants.SYSTEM_ERROR);
		}
		//返回验证码
		return mobile_code;
	}
	
	/**
	 * 获取验证码<br>
	 * 1/验证内存中是否存在<br>
	 * 2/不存在返回null<br>
	 * 3/存在返回内存中的验证码<br>
	 * 2017年1月4日 下午3:03:10<br>
	 * get_code<br>
	 * 
	 * @param phone
	 *            手机号码<br>
	 * @return
	 * @return String
	 */
	private String get_memory_code(String phone)
	{
		String[] infos = CODES.get(phone);
		//如果内存中存在该手机号码的验证码
		if(!ValidatorUtils.isEmpty(infos)){
			System.out.println(JSONUtils.toJSONString(infos));
			//验证验证码时间是否超时
			//生成验证码时的时间
			Long create_time = Long.valueOf(infos[2]);
			//当前时间
			Long local_time = System.nanoTime();
			//时间戳秒数
			long second = TimeUnit.NANOSECONDS.toSeconds(local_time - create_time);
			//如果时间戳小于等于30分钟返回内存中的验证码
			if(second <= (60 * 60 * 30))
			{
				return infos[1];	
			}
			//如果超过了30分钟，删除内存中的验证码信息
			else
			{
				CODES.remove(phone);
			}
		}
		return null;
	}
}
