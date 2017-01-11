package com.card.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 开启springboot项目支持swagger2 <br>
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：Swagger2 <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月3日 上午11:08:45<br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月3日 上午11:08:45<br>
 * 修改备注：<br>
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	/**
	 * 创建api对象<br>
	 * 2017年1月3日 上午11:09:13 createRestApi
	 * 
	 * @return
	 * @return Docket
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.card.api")).paths(PathSelectors.any()).build();
	}

	/**
	 * 设置api基本信息描述<br>
	 * 2017年1月3日 上午11:09:40<br>
	 * apiInfo
	 * 
	 * @return
	 * @return ApiInfo
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("91信用卡API接口列表").description("API列表适用于：安卓、IOS、微信公众账号、官网等。").contact("于起宇")
				.version("1.0").build();
	}

}
