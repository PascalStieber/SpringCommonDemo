package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@Order(-20)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("Peter").password("{noop}peter").roles("USER");
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers("/login", "/oauth/authorize", "/auth/token").and().authorizeRequests().anyRequest()
				.authenticated().and().formLogin().permitAll();
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.requestMatchers().antMatchers("/login.html", "/login-error.html", "/oauth/authorize", "/oauth/token_key", "/initAdminUser").and().authorizeRequests()
//		.anyRequest().authenticated().and().formLogin().loginPage("/login.html").failureUrl("/login-error.html").permitAll();
//	}

	// @Bean
	// @Override
	// public AuthenticationManager authenticationManagerBean() throws Exception {
	// return super.authenticationManagerBean();
	// }
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


	// @Autowired
	// private AuthenticationManager authenticationManager;

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.parentAuthenticationManager(authenticationManager).inMemoryAuthentication().withUser("john")
	// .password("123").roles("USER");
	// }

}