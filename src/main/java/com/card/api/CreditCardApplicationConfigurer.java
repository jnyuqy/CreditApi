package com.card.api;

import java.util.List;

import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import javax.persistence.EntityManager;

/**
 * spring boot 系统配置 项目名称：CreditCard<br>
 * 项目版本：V1.0 <br>
 * 类名称：CreditCardApplicationConfigurer <br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月16日 下午3:30:01 <br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月16日 下午3:30:01 <br>
 * 修改备注：<br>
 */
@Configuration
public class CreditCardApplicationConfigurer extends WebMvcConfigurerAdapter {
	/**
	 * 使用FastJson第三方Json处理工具，配置Json视图渲染
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(fastConverter);
	}
}
