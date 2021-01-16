package com.jslsolucoes.appointment.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30).select()
				.apis(handler -> RequestHandlerSelectors.basePackage("com.jslsolucoes.appointment.api.resource").test(handler))
				.paths(input -> PathSelectors.any().test(input)).build();
	}
}