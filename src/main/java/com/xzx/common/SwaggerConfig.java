package com.xzx.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@Profile({"dev"})
public class SwaggerConfig {

	@Value("${swagger.enabled}")
	private String enabled;
	
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.enable(Boolean.parseBoolean(enabled))
                .apiInfo(apiInfo())
                .select()
                //.paths(PathSelectors.none())//如果是线上环境，添加路径过滤，设置为全部都不符合 
                .apis(RequestHandlerSelectors.basePackage("com.xzx.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("牡丹江司法局终端项目接口文档")
                .description("所有接口返回成功/失败标识:Code(00000000——成功；11111111——失败)")
                .termsOfServiceUrl("https://gitee.com/HelenLee/LawApiProject.git")
                .version("1.0")
                .build();
    }
}