package com.card.core.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 类型转换工具类
 * 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：ParseUtils <br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月23日 上午10:17:09 <br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月23日 上午10:17:09 <br>
 * 修改备注：<br>
 */
public final class ParseUtils {
	
	/**
	 * 将字符串转换为integer类型 <br>
	 * 2016年12月23日 上午10:17:35 toInteger
	 * 
	 * @param val
	 *            未被转换的值
	 * @return Integer 转换后的值
	 */
	public static Integer toInteger(String val) {
		return Integer.valueOf(val);
	}

	/**
	 * 将object转换为string类型 <br>
	 * 2016年12月23日 上午10:17:35 toString
	 * 
	 * @param obj
	 *            未被转换的值
	 * @return String 转换后的值
	 */
	public static String toString(Object obj) {
		return obj.toString().trim();
	}
	
	/**
	 * 将string类型转换为double类型
	 * <br>
	 * 2016年12月23日 上午10:18:57 toDouble
	 * 
	 * @param obj 未被转换的值
	 * @throws NumberFormatException
	 * @return Double 转换后的值
	 */
	public static Double toDouble(String obj) throws NumberFormatException {
		return Double.valueOf(obj);
	}
	
	/**
	 * 将object类型的值转换为double <br>
	 * 2016年12月23日 上午10:19:46 toDouble
	 * 
	 * @param obj
	 *            未被转换的值
	 * @throws NumberFormatException
	 * @return Double 转换后的值
	 */
	public static Double toDouble(Object obj) throws NumberFormatException {
		return Double.valueOf(toString(obj));
	}
	
	/**
	 * 将double类型格式化成0.000保留三位小数点
	 * <br>
	 * 2016年12月23日 上午10:20:50 toDouble
	 * 
	 * @param val 未被格式化的值
	 * @return
	 * @return Double 格式化后的值
	 */
	public static Double toDouble(Double val) {
		if (val != null) {
			DecimalFormat format = new DecimalFormat("#0.000");
			return Double.valueOf(Double.parseDouble(format.format(val)));
		}
		return new Double(0.0D);
	}
	
	/**
	 * 将object类型的值转换为float <br>
	 * 2016年12月23日 上午10:22:02 toFloat
	 * 
	 * @param obj
	 *            未被转换的值
	 * @return Float 转换后的值
	 */
	public static Float toFloat(Object obj) {
		return Float.valueOf(Float.parseFloat(toString(obj)));
	}
	
	/**
	 * 将object类型的值转换为integer <br>
	 * 2016年12月23日 上午10:22:41 toInteger
	 * 
	 * @param obj 未被转换的值
	 * @throws NumberFormatException
	 * @return Integer 转换后的值
	 */
	public static Integer toInteger(Object obj) throws NumberFormatException {
		return Integer.valueOf(toString(obj));
	}
	
	/**
	 * 将数组转换为字符串,格式化后字符串：str1,str2,str3<br>
	 * 2016年12月23日 上午10:23:14 array2String
	 * 
	 * @param objs
	 * @return
	 * @return String
	 */
	public static String array2String(Object[] objs) {
		String str = "";
		for (int i = 0; i < objs.length; i++) {
			str = str + objs[i] + (i == objs.length - 1 ? "" : ",");
		}
		return str;
	}

	/**
	 * 将string字符串转换根据pattern分割成数组 <br>
	 * 2016年12月23日 上午10:24:57 toArray
	 * 
	 * @param obj 未被分割的数组
	 * @param pattern 分割的标识
	 * @return String[] 格式化后的数组
	 */
	public static String[] toArray(String obj, String pattern) {
		return obj.toString().split(pattern);
	}
	
	/**
	 * 将时间转换根据formatter转换为string<br>
	 * 2016年12月23日 上午10:25:53 date2String
	 * 
	 * @param date 日期
	 * @param formatter 格式化标识
	 * @return String 格式化后的字符串
	 */
	public static String date2String(Date date, String formatter) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}

	/**
	 * 获取字符串
	 * 
	 * @param str
	 *            字符串
	 * @param num
	 *            倒数分隔符的位数
	 * @return
	 */
	public static String getSubStr(String str, int num) {
		String result = "";
		int i = 0;
		while (i < num) {
			int lastFirst = str.lastIndexOf('/');
			result = str.substring(lastFirst) + result;
			str = str.substring(0, lastFirst);
			i++;
		}
		return result.substring(1);
	}

	/**
	 * 将字符串转成unicode
	 * 
	 * @param str
	 *            待转字符串
	 * @return unicode字符串
	 */
	public static String convertUnicode(String str) {
		str = (str == null ? "" : str);
		String tmp;
		StringBuffer sb = new StringBuffer(1000);
		char c;
		int i, j;
		sb.setLength(0);
		for (i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			sb.append("\\u");
			j = (c >>> 8); // 取出高8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);
			j = (c & 0xFF); // 取出低8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);

		}
		return (new String(sb));
	}
}
