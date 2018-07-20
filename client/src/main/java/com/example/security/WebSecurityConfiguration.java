package com.example.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableOAuth2Sso
@Configuration
@Order(-10)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/**").authorizeRequests().antMatchers("/", "/actuator/**", "/info").permitAll()
				.anyRequest().authenticated();
//		.and().oauth2Login().loginProcessingUrl("/client/login");
	}

}
