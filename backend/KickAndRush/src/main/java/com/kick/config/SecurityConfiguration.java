package com.kick.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	public static final String ADMIN = "ADMIN";
    public static final String MEMBER = "MEMBER";
//	  private final TokenAuthenticationFilter tokenAuthenticationFilter;

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
//    @Bean
//    @Order(SecurityProperties.BASIC_AUTH_ORDER)
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//세선 사용 X
//        	.and()
//        	.formLogin().disable() //폼 로그인 사용 X
//	        .httpBasic().disable() // http 방식 사용 x
//	        .authorizeRequests()
//	        .antMatchers("/api/member/**")
//	        .hasRole("Member")
//	        .access("hasRole('Member')")
//	        .antMatchers("/api/admin/**")
//			.access("hasRole('ADMIN')")
//			.anyRequest().permitAll();
//	        .permitAll();
//        
//        return http.build();
//    }
//	
	 @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http
         	.csrf().disable();
		 http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//세선 사용 X
	     	.and()
	     	.formLogin().disable() //폼 로그인 사용 X
	        .httpBasic().disable() // http 방식 사용 x
	        .authorizeRequests()
	        .antMatchers("/api/member/**")
	        .hasRole("MEMBER")
			.anyRequest().permitAll();

		 return http.build();
    }

    @Bean 
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
}
