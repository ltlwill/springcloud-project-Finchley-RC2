package com.efe.ms.productservice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
 * <p>
 * swagger2启用配置
 * </p>
 * 
 * @author liutianlong 2018年6月8日 上午11:43:46
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.efe.ms.productservice.controller"))
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.apis(RequestHandlerSelectors
						.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any()).build();
	}

	/**
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param
	 * @author liutianlong
	 * @date 2018年6月8日 上午11:46:05
	 * @return ApiInfo
	 */
	@SuppressWarnings("deprecation")
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("基础产品服务").description("基础产品服务文档")
//				.termsOfServiceUrl("http://www.hengzhiyi.cn")
				.contact("liutl@iefiel.com").version("1.0").build();
	}
}
