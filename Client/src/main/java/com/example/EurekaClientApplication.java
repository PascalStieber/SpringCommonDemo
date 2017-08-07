package com.example;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.web.context.request.RequestContextListener;

@EnableOAuth2Sso
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientApplication extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	@Override
	public void configure(HttpSecurity http) throws Exception {
	
		http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**").permitAll().anyRequest()
				.authenticated();

		http.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
	}

	@Autowired
	OAuth2ProtectedResourceDetails resourceDetails;
	@Autowired
	OAuth2ClientContext OAuth2ClientContext;

	@Autowired
	ResourceServerProperties resourceServerProperties;

	private Filter ssoFilter() {
//		if(oauth2ClientContext.getAccessToken()!=null)
		OAuth2ClientAuthenticationProcessingFilter oAuuth2Filter = new OAuth2ClientAuthenticationProcessingFilter(
				"/securedPage");
		OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(resourceDetails, OAuth2ClientContext);
		oAuuth2Filter.setRestTemplate(facebookTemplate);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(resourceServerProperties.getUserInfoUri(),
				resourceServerProperties.getClientId());
		tokenServices.setRestTemplate(facebookTemplate);
		oAuuth2Filter.setTokenServices(tokenServices);
		return oAuuth2Filter;
	}

	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

}