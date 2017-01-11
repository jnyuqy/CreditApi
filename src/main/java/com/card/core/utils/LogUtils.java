package com.card.core.utils;

import org.apache.log4j.Logger;

/**
 * 
*    
* 项目名称：CreditCardCore
* 项目版本：V1.0   
* 类名称：LogUtils   
* 创建人：yuqy   
* 创建时间：2016年12月16日 上午9:56:05   
* 修改人：yuqy   
* 修改时间：2016年12月16日 上午9:56:05   
* 修改备注：
 */
public class LogUtils {
    
    private Logger logger;
    
    /**
     * 创建一个新的实例 LogUtils.
     * 
     * @param clazz
     */
    public LogUtils(Class<?> clazz) { logger = Logger.getLogger(clazz); }
    
    /**
     * 2016年12月16日 上午9:56:11 info
     * 
     * @param params 可变参数数量参数，如：param1,param2,....
     * @throws Exception
     * @return void
     */
    public void info(Object...params)
    {
        logger.info(JSONUtils.toJSONString(params));
    }
}
