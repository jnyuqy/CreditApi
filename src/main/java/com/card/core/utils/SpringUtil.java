package com.card.core.utils;

import org.springframework.context.ApplicationContext;

/**
 * spring工具类，可根据类型获取注入的实体bean <br>
 * 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：SpringUtil <br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月26日 上午10:36:12<br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月26日 上午10:36:12<br>
 * 修改备注：<br>
 */
public class SpringUtil {
	
	//springboot启动时实例化该对象
	public static ApplicationContext applicationContext;
	
	/**
	 * 根据class类型获取spring注入的实例化对象 <br>
	 * 2016年12月26日 上午10:36:51 <br>
	 * getSpringClassName
	 * 
	 * @param clazz 需要获取实例化对象的类型
	 */
	public static Object getSpringClassName(Class<?> clazz) {
		if (applicationContext == null) {
			throw new NullPointerException("获取ApplicaitonContext对象失败!");
		}
		return applicationContext.getBean(clazz);
	}
	
	/**
	 * 根据spring管理的类名进行获取对象
	* 2016年12月29日 下午6:12:24
	* getSpringObjectByName
	* @param className
	* @return
	* @return Object
	 */
	public static Object getSpringObjectByName(String className)
	{
		if (applicationContext == null) {
			throw new NullPointerException("获取ApplicaitonContext对象失败!");
		}
		return applicationContext.getBean(className);
	}
}
