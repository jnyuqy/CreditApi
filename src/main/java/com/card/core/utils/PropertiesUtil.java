package com.card.core.utils;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 解析xx.properties文件工具类<br>
 * 可根据key获取value值<br>
 * 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：PropertiesUtil <br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月17日 上午10:18:33 <br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月17日 上午10:18:33 <br>
 * 修改备注：<br>
 */
public class PropertiesUtil
{
  private final ResourceBundle resource;
  private final String fileName;
  
    /**
     * 构造函数实例化部分对象，获取文件资源对象
     * 
     * @param fileName
     */
  public PropertiesUtil(String fileName)
  {
    this.fileName = fileName;
    Locale locale = new Locale("zh", "CN");
    this.resource = ResourceBundle.getBundle(this.fileName, locale);
  }

    /**
     * 根据传入的key获取对象的值 2016年12月17日 上午10:19:55 getValue
     * 
     * @param key properties文件对应的key
     * @param params 参数列表，String.format
     * @return String 解析后的对应key的值
     */
  public String getValue(String key, Object...params)
  {
    String message = this.resource.getString(key);
    try {
      message = new String(message.getBytes("ISO8859-1"), "UTF-8");
    }
    catch (Exception localException) {
    }
    if (!ValidatorUtils.isNull(params))
    {
      message = MessageFormat.format(message, params);
    }
    return message;
  }
  
    /**
     * 获取properties文件内的所有key值<br>
     * 2016年12月17日 上午10:21:20 getKeys
     * 
     * @return
     */
  public Enumeration<String> getKeys(){
	  return resource.getKeys();
  }
}
