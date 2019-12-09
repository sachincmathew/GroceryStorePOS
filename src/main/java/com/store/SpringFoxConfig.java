package com.store;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.ant("/api/**")).build();
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("Grocery Store POS",
				"The application will expose a set of restful APIs that interface with any frontend GUI for a full fleged POS application for a grocery store.",
				"1.0.0", "TERMS OF SERVICE URL", new Contact("Sachin Cherian Mathew", "URL", "sachincmathew@gmail.com"),
				"LICENSE", "LICENSE URL", Collections.emptyList());
	}

}