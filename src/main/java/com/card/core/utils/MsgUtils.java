package com.card.core.utils;

import java.util.Enumeration;

import com.card.core.constants.SysConstants;
/**
 * //+----------------------------------------------------------------------
//| 异常消息处理工具�?
//+----------------------------------------------------------------------
//| 
//+----------------------------------------------------------------------
//| Copyright (c) 2016 yuqy All rights reserved.
//+----------------------------------------------------------------------
//| Author: yuqy <960758921@qq.com>
//+----------------------------------------------------------------------
 */
public class MsgUtils
{
    private LogUtils logUtils = new LogUtils(this.getClass());
	/**
	 * 根据异常码获取异常信
	 * @param messageId
	 * @param params
	 * @return
	 */
	public String getValue(String messageId,Object...params){
	    
	    //如果内存不存在系统消息集合，那么初始化将系统消息存放到内存中
	    if(ValidatorUtils.isEmpty(SysConstants.MESSAGES))
	    {
	        putValuesToRam();
	    }
	    
		//获取异常消息内容
		String message = SysConstants.MESSAGES.get(messageId);
		//抛出未检索到异常
		if(ValidatorUtils.isEmpty(message)){
			throw new NullPointerException("未获取到,错误码："+messageId+" , 异常消息内容!");
		}
		//把参数格式化到字符串
		message = String.format(message, params);
		return message;
	}
	
    /**
     * 加载系统消息到内存
     * 2016年12月17日 上午10:36:33 putValuesToRam
     * 
     * @return void
     */
	private void putValuesToRam()
	{
	    logUtils.info("开始加载系统消息到内存中....");
        PropertiesUtil propertiesUtil = new PropertiesUtil("message");
        Enumeration<String> keys = propertiesUtil.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = propertiesUtil.getValue(key);
            SysConstants.MESSAGES.put(key, value);
			logUtils.info("消息编号", key, "消息未格式化前内容", value);
        }
        logUtils.info("系统消息加载完成.");
	}
}
