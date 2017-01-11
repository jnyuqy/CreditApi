package com.card.core.utils;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 验证数据有效性工具类
*    
* 项目名称：CreditCardCore
* 项目版本：V1.0   
* 类名称：ValidatorUtils   
* 创建人：yuqy   
* 创建时间：2016年12月15日 下午4:42:48   
* 修改人：yuqy   
* 修改时间：2016年12月15日 下午4:42:48   
* 修改备注：
 */
public class ValidatorUtils {

    // 禁止实例化
    private ValidatorUtils(){}
    
    /**
     * 判断是否为浮点数或者整数 <br>2016年12月15日 下午4:43:13 isNumeric
     * 
     * @param str
     *            验证字符串
     * @return true : 字符串是浮点或者整数，false : 字符串内容不是浮点或者整数
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否为正确的邮件格式 <br>2016年12月15日 下午4:45:08 isEmail
     * 
     * @param str
     *            验证字符串
     * @return true : 字符串是邮件地址，false : 字符串内容不是邮件地址
     */
    public static boolean isEmail(String str) {
        if (isEmpty(str))
            return false;
        return str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
    }

    /**
     * 判断字符串是否为合法手机号 11位 13 14 15 18开头 
     * <br>2016年12月15日 下午4:46:37 isMobile
     * 
     * @param str
     *            验证字符串
     * @return true : 字符串是手机号码，false : 字符串内容不是手机号码
     */
    public static boolean isMobile(String str) {
        if (isEmpty(str))
            return false;
        return str.matches("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$");
    }

    /**
     * 判断是否为数字 <br>
     * 2016年12月15日 下午4:47:36 isNumber
     * 
     * @param str
     *            验证字符串
     * @return true : 字符串是整数，false : 字符串内容不是整数
     */
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 判断字符串是否为非空(包含null与"") <br>
     * 2016年12月15日 下午4:48:33 isNotEmpty
     * 
     * @param str
     *            验证字符串
     * @return true : 字符串非空，false : 字符串为空
     */
    public static boolean isNotEmpty(String str) {
        if (str == null || "".equals(str))
            return false;
        return true;
    }

    /**
     * 判断字符串是否为非空(包含null与""," ") <br>
     * 2016年12月15日 下午4:50:08 isNotEmptyIgnoreBlank
     * 
     * @param str
     *            验证字符串
     * @return true : 字符串非空，false : 字符串为空
     */
    public static boolean isNotEmptyIgnoreBlank(String str) {
        if (str == null || "".equals(str) || "".equals(str.trim()))
            return false;
        return true;
    }
    
    /**
     * 判断对象是否为null <br>
     * 2016年12月15日 下午4:52:04 isNull
     * 
     * @param object
     *            验证对象
     * @return true : 传入对象为NULL, false : 传入对象不为NULL
     */
    public static boolean isNull(Object object)
    {
      if (object == null) {
        return true;
      }
      return false;
    }

    /**
     * 判断传入的对象（普通对象，List,Map,String）是否为空 <br>
     * 2016年12月15日 下午4:56:04 isEmpty
     * 
     * @param object
     *            验证对象
     * @return true : 当对象为null或者长度为0，false : 当对象不为null并且长度大于0
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object object)
    {
      boolean flag = false;
      //为null直接返回true
      if (isNull(object)) {
        return true;
      }
      //判断list集合size是否为0
      if ((object instanceof List)) {
        flag = ((List)object).size() == 0;
      }
      //判断string类型length是否为0
      else if ((object instanceof String)) {
        flag = ((String)object).trim().length() == 0;
      }
      //判断map集合size是否为0
      else if ((object instanceof Map)) {
        flag = ((Map)object).size() == 0;
      }
      //判断是否为long类型
      else if((object instanceof Long)){
          flag = Long.valueOf(ParseUtils.toString(object)) == 0;
      }
      return flag;
    }
    
    /**
     * 判断传入的整数是否为0 <br>
     * 2016年12月15日 下午5:16:43 isZero
     * 
     * @param object
     *            验证对象
     * @return true : 对象为0，false : 对象不为0
     */
    public static boolean isZero(int object)
    {
      if (object == 0) {
        return true;
      }
      return false;
    }
    
    /**
     * 判断传入的浮点型是否为0 <br>
     * 2016年12月15日 下午5:17:54 isZero
     * 
     * @param obj
     *            验证对象
     * @return true : 浮点对象为0,false : 浮点对象不为0
     */
    public static boolean isZero(Double obj) {
        if (obj.doubleValue() == 0.0D) {
            return true;
        }
        return false;
    }
}
