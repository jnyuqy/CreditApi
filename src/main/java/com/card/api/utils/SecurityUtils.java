package com.card.api.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import com.card.core.constants.SysMsgConstants;
import com.card.core.exception.LogicException;
import com.card.core.security.Base64;
import com.card.core.security.MD5;
import com.card.core.utils.JSONUtils;
import com.card.core.utils.ParseUtils;
import com.card.core.utils.ValidatorUtils;

/**
 * 
 * restful安全验证 <br>
 * 验证流程：
 * 1、验证请求客户端IP地址
 * 2、验证sign
 * 3、验证api_key
 * 4、验证时间戳
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：SecurityUtils<br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月3日 下午4:49:12<br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月3日 下午4:49:12<br>
 * 修改备注：<br>
 */
public class SecurityUtils {

	//存放所有可以访问api列表的客户端ip地址
	private static final HashSet<String> CLIENT_IPS = new HashSet<String>() {
		/**  
		* 序列号
		* {@value}
		*/ 
		private static final long serialVersionUID = 1975961668441779741L;

		{
			add("127.0.0.1");
		}
	};
	//存放所有可以访问api列表的客户端api_key
	private static final HashSet<String> API_KEYS = new HashSet<String>() {

		/**  
		* 序列号
		* {@value}
		*/ 
		private static final long serialVersionUID = 8841035921670196086L;

		{
			add("OUM2NEY3NDA3MUNGMjJFOTBGQ0JERUJDMkRCNUJFQ0Q=");//安卓api_key
			add("QTYyQjcxREJDQzQzNjhDNUUxQjkwMkZEMEYyNzhBN0Q=");//ios,api_key
		}
	};
	//时间戳
	public static String _TIME = "time";
	//api_key
	public static String _API_KEY = "api_key";
	//客户端ip地址
	public static String _CLIENT_IP = "client_ip";
	//签名
	public static String _SIGN = "sign";
	//参数列表
	private static Hashtable<String, Object> params;
	//生成签名时的追加字符串
	private static final String SIGN_XX = "xunmei@keji.com";
	
	/**
	 * 获取请求客户端的ip外网地址 2017年1月3日 下午5:18:39<br>
	 * getCliectIp<br>
	 * 
	 * @param request<br>
	 * @return<br>
	 * @return String
	 */
	public static String getCliectIp(HttpServletRequest request)
	{
		String ip = request.getHeader("x-forwarded-for");
		if (ValidatorUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
	    	ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ValidatorUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
	    	ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ValidatorUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
	    	ip = request.getRemoteAddr();
	    }
	 
	    // 多个路由时，取第一个非unknown的ip
	    final String[] arr = ip.split(",");
	    for (final String str : arr) {
	        if (!"unknown".equalsIgnoreCase(str)) {
	        	ip = str;
	            break;
	        }
	    }
	    System.out.println("-----------------客户端IP地址："+ip+"-----------------");
		return ip;
	}
	
	/**
	 * 验证统一请求<br>
	 * 2017年1月3日 下午5:09:54 validate
	 * 
	 * @return
	 * @return boolean
	 */
	public static synchronized boolean validate(Hashtable<String, Object> params) throws LogicException{
		//参数赋值
		SecurityUtils.params = params;
		System.out.print(JSONUtils.toJSONString(params));
		//ip地址验证不通过
		if(!validateIp())
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "客户端IP地址验证失败", "请求");
		}
		//时间戳验证不通过
		else if(!validateTime())
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "该请求已经超时", "请求");
		}
		//api_key验证不通过
		else if(!validateApiKey())
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "客户端api_key验证失败", "请求");
		}
		//签名验证不通过
		else if(!validateSign())
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "客户端sign验证失败", "请求");
		}
		return true;
	}
	
	/**
	 * 验证客户端ip是否可以访问restful接口列表
	 * <br>
	 * 2017年1月3日 下午5:19:52 validateIp
	 * 
	 * @return
	 * @return boolean
	 */
	private static boolean validateIp(){
		//return CLIENT_IPS.contains(params.get(_CLIENT_IP));
		return true;
	}
	
	/**
	 * 验证客户端发送请求自带参数api_key是否授权访问列表<br>
	 * 2017年1月3日 下午5:56:30<br>
	 * validateApiKey<br>
	 * 
	 * @return
	 * @return boolean
	 */
	private static boolean validateApiKey(){
		return API_KEYS.contains(params.get(_API_KEY));
	}
	
	/**
	 * 无视3分钟之前的请求<br>
	 * 2017年1月3日 下午5:14:40<br>
	 * validateTime<br>
	 * 
	 * @return
	 * @return boolean
	 */
	private static boolean validateTime(){
		//客户端请求时间
		long client_time = Long.parseLong(ParseUtils.toString(params.get(_TIME)));
		//服务器本地时间
		long local_time = System.currentTimeMillis();
		//时间戳
		long second = TimeUnit.MILLISECONDS.toSeconds(local_time - client_time);
		System.out.println("客户端时间戳："+client_time);
		System.out.println("服务时间戳："+local_time);
		System.out.println("客户端与服务器时间戳相减：" + second);
		//请求时间小于180秒，证明三分钟内的请求
		return second == 0 || (second > 0 && second < 300);
		//return true;
	}
	
	/**
	 * 获取签名<br>
	 * 2017年1月3日 下午6:01:20<br>
	 * validateSign<br>
	 * @return<br>
	 * 
	 * @return boolean
	 */
	private static boolean validateSign(){
		//获取客户端请求的sign值
		String client_sign = ParseUtils.toString(params.get(_SIGN));
		//排除参数集合内的sign不参与生成sign
		params.remove(_SIGN);
		//排除参数集合内的client_ip不参与生成sign
		params.remove(_CLIENT_IP);
		//临时存在params的key列表
		List<String> _temp_param_keys = new ArrayList<String>();
		Iterator<String> iterator = params.keySet().iterator();
		while(iterator.hasNext())
		{
			_temp_param_keys.add(iterator.next());
		}
		//排序集合
		Collections.sort(_temp_param_keys);
		//请求路径
		StringBuffer url = new StringBuffer();
		//生成请求路径
		for (String key : _temp_param_keys) {
			url.append(key + "=" + params.get(key) + "&");
		}
		System.out.println("请求路径：" + url);
		//生成sign
		String sign = Base64.encode((SIGN_XX + MD5.MD5(url.toString())).getBytes());
		System.out.println("sign : " + sign);
		System.out.println("client_sign : " + client_sign);
		//返回sign比对
		return client_sign.trim().equals(sign);
		
	}
	
	public static void main(String[] args) {
		System.out.println(MD5.MD5("123456"));
		System.out.println(System.currentTimeMillis());
	}
}
