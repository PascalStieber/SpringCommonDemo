package com.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http
	// .authorizeRequests()
	// .anyRequest()
	// .fullyAuthenticated()
	// .and()
	// .formLogin()
	// .permitAll()
	// .and()
	// .logout()
	// .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	// .logoutSuccessUrl("/home")
	// .and()
	// .anonymous().disable()
	// .csrf().disable()
	// ;
	// }
	//
	// @Autowired
	// DataSource dataSource;

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.jdbcAuthentication().dataSource(dataSource)
	// .usersByUsernameQuery("SELECT email,password,enabled from user where
	// email=?")
	// .authoritiesByUsernameQuery("select email,role from user where email=?");
	// }

	@Bean
	public UserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/token_key", "/initAdminUser").and().authorizeRequests()
				.anyRequest().authenticated().and().formLogin().permitAll();
	}

	// @Autowired
	// private AuthenticationManager authenticationManager;

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.parentAuthenticationManager(authenticationManager).inMemoryAuthentication().withUser("john")
	// .password("123").roles("USER");
	// }

}