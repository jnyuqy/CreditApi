package com.card.core.constants;

/**
 * 系统消息常量<br>
 * 其中%s为传入字符串参数,如： <br>
 * msgUtils.getValue(SysMsgConstants.EXEC_IS_ERROR, SysMsgParamsConstants.XXX)<br>
 * 其中XXX为自定义字符串
 * <p>
 * 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：SysMsgConstants<br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月15日 下午4:40:10<br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月15日 下午4:40:10<br>
 * 修改备注：<br>
 */
public interface SysMsgConstants {

    /**
     * <p>
     * 传入参数异常
     * <p>
     */
    public static final String PARAM_IS_NULL = "PARAM_IS_NULL";
    /**
     * <p>
     * 系统异常
     * <p>
     */
    public static final String SYSTEM_ERROR = "SYSTEM_ERROR";

    /**
     * <p>
     * %s失败
     * <p>
     */
    public static final String EXEC_IS_ERROR = "EXEC_IS_ERROR";

    /**
     * <p>
     * %s成功
     * <p>
     */
    public static final String EXEC_IS_SUCCESS = "EXEC_IS_SUCCESS";
    
    /** %s,%s失败 */
    public static final String XX_ERROR = "XX_ERROR";

}
