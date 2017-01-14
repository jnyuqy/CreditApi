package com.card.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 91信用卡api项目入口
 * <br>
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：CreditCardApiApplication<br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月3日 上午10:42:47<br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月3日 上午10:42:47<br>
 * 修改备注：<br>
 */
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class CreditCardApiApplication extends SpringBootServletInitializer
{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.sources(CreditCardApiApplication.class);
		return super.configure(application);
	}
	
	//springboot程序入口
	public static void main(String[] args) {
		SpringApplication.run(CreditCardApiApplication.class, args);
	}
}
