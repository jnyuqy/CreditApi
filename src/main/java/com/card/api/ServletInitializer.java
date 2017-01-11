package com.card.api;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 替换了传统的web.xml的功能<br>
 * 配置该类后，CreditCardApiApplication类中不需要继承SpringBootServletInitializer<br>
 * 否则项目会启动两边并抛出异常<br>
 * 
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：ServletInitializer<br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月5日 上午11:02:03<br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月5日 上午11:02:03<br>
 * 修改备注：
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CreditCardApiApplication.class);
	}

}
