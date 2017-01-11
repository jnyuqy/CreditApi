package com.card.core.constants;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.card.core.security.MD5;

/**
 * 系统常量设置<br>
 * 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：SysConstants <br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月15日 下午4:39:16 <br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月15日 下午4:39:16 <br>
 * 修改备注：<br>
 */
public interface SysConstants {

    /** 存在session内的登录用户标识 */
    public static final String SESSION_USER = MD5.MD5("SESSION_USER");

    /** 存在session内的登录用户菜单列表 */
    public static final String SESSION_MENUS = MD5.MD5("SESSION_MENUS");
    
    /** 存在session内的登录用户按钮列表 */
    public static final String SESSION_BUTTONS = MD5.MD5("SESSION_BUTTONS");
    
    /** 内存中存放所有异常消息，每次在内存加载，不再每次读取properties文件 */
    public static final Hashtable<String, String> MESSAGES = new Hashtable<String, String>();
    
    /** 不被session拦截的路径 */
	public static final List<String> NO_SESSION_URLS = new ArrayList<String>() {
		/**
		 * 序列号
		 */
		private static final long serialVersionUID = 4781416983826209760L;
		{
			add("/login");
			add("/logining");
		}
	};
}
