package com.card.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解来完成获取字段的注释
*   该注解仅作用于字段
* 项目名称：CreditCardCore
* 项目版本：V1.0   
* 类名称：Comment   
* 创建人：yuqy   
* 创建时间：2016年12月28日 下午5:32:53   
* 修改人：yuqy   
* 修改时间：2016年12月28日 下午5:32:53   
* 修改备注：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Comment {
	//注释内容
	String value();
}
