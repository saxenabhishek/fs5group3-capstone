package com.fidelity.application;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages={"com.fidelity.integration", "com.fidelity.controller", "com.fidelity.service", "com.fidelity.business"})
@MapperScan(basePackages="com.fidelity.integration.mapper")
public class FidTradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FidTradeApplication.class, args);
	}

	@Bean
	@Scope("prototype")
	Logger createLogger(InjectionPoint ip) {
	    Class<?> classThatWantsALogger = ip.getField().getDeclaringClass();
	    return LoggerFactory.getLogger(classThatWantsALogger);
	}
	
	 @Bean
	    public RestTemplate restTemplate() {
	        RestTemplate restTemplate = new RestTemplate();

	        restTemplate.setMessageConverters(List.of(new MappingJackson2HttpMessageConverter()));

	        return new RestTemplate();
	    }
}
