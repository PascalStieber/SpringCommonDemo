package com.example;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.request.RequestContextListener;

@EnableOAuth2Sso
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientApplication extends WebSecurityConfigurerAdapter {

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/**").authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated();

        http.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }

    
    @Autowired
    OAuth2ProtectedResourceDetails resourceDetails;
    @Autowired
    OAuth2ClientContext            OAuth2ClientContext;

    @Autowired
    ResourceServerProperties       resourceServerProperties;

    private Filter ssoFilter() {
        // OAuth2ClientAuthenticationProcessingFilter oAuuth2Filter = new OAuth2ClientAuthenticationProcessingFilter(
        // "/securedPage");
        SsoFilter oAuth2Filter = new SsoFilter("/securedPage");
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, OAuth2ClientContext);
        oAuth2Filter.setRestTemplate(restTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(resourceServerProperties.getUserInfoUri(), resourceServerProperties.getClientId());
        tokenServices.setRestTemplate(restTemplate);
        oAuth2Filter.setTokenServices(tokenServices);

        oAuth2Filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler() {
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                    throws IOException, ServletException {
                this.setDefaultTargetUrl("http://192.168.99.101:9999/client/securedPage2");
//                this.setDefaultTargetUrl("http://localhost:9999/client/securedPage2");
                super.onAuthenticationSuccess(request, response, authentication);
            }
        });
        return oAuth2Filter;
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