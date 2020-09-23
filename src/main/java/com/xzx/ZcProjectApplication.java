package com.xzx;

import javax.servlet.MultipartConfigElement;

import org.apache.catalina.connector.Connector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableScheduling
@EnableAsync
@EnableSwagger2
@MapperScan("com.xzx.dao") //扫描的mapper
@Configuration
@SpringBootApplication
@EnableTransactionManagement
public class ZcProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZcProjectApplication.class, args);
		System.out.println("\n\n<<<<<<<<<<<<<<<<<<<<<<<<< 启动完成，试试调用API 吧 ^_^ >>>>>>>>>>>>>>>>>>>>>>>>\n");
	}

	@Value("${file.uploadFolder}")
	private String uploadFolder;

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation(uploadFolder);

//		//文件最大
//		factory.setMaxFileSize(DataSize.of(100, DataUnit.MEGABYTES));
//		// 设置总上传数据总大小
//		factory.setMaxRequestSize(DataSize.of(100, DataUnit.MEGABYTES)); 

		return factory.createMultipartConfig();
	}

	/** 
	 * 跨域过滤器 
	 * @return 
	 */  
	@Bean  
	public CorsFilter corsFilter() {  
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);  
	}  
	/**CORS跨域配置
	 * @return
	 */
	private CorsConfiguration buildConfig() {  
		CorsConfiguration corsConfiguration = new CorsConfiguration();  
		corsConfiguration.addAllowedOrigin("*"); //允许的域名或IP地址
		corsConfiguration.addAllowedHeader("*"); //允许的请求头
		corsConfiguration.addAllowedMethod("*"); //允许的HTTP请求方法
		corsConfiguration.setAllowCredentials(true); //允许发送跨域凭据，前端Axios存取JSESSIONID必须要
		return corsConfiguration;  
	}

	@Value("${myhttp.port}")
	private Integer httpPort;

	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(createHTTPConnector());
		return tomcat;
	}

	private Connector createHTTPConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setSecure(false);
		connector.setPort(httpPort);
		return connector;
	}
}
