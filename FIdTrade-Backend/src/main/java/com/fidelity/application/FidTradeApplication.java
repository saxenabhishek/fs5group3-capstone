package com.fidelity.application;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
@ComponentScan(basePackages={"com.fidelity.integration", "com.fidelity.controller", "com.fidelity.service", "com.fidelity.business"})
@MapperScan(basePackages="com.fidelity.mapper")
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
}
