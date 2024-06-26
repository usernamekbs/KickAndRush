package com.kick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing
public class KickAndRushApplication {

	public static void main(String[] args) {
		SpringApplication.run(KickAndRushApplication.class, args);
	}

	@Bean
	public PageableHandlerMethodArgumentResolverCustomizer customize() {
		return p -> {
			p.setOneIndexedParameters(true);	// 페이지 1부터 시작 기본 default는 false
		};
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}
}
