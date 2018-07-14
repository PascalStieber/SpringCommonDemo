package com.example;

import java.security.KeyPair;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@Order(-10)
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.allowFormAuthenticationForClients()
		.tokenKeyAccess("permitAll()")
				.checkTokenAccess("permitAll()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("SampleClientId").secret("{noop}secret").authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials")
				.scopes("user_info").autoApprove(true);
	}

	// @Autowired
	// private AuthenticationManager authenticationManager;

	// commandLine: keytool -genkey -keyalg RSA -alias selfsigned_new -keystore
	// keystore_new.jks -storepass password -validity 360 -keysize 2048

	// @Bean
	// public JwtAccessTokenConverter jwtAccessTokenConverter() {
	// JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	// KeyPair keyPair = new KeyStoreKeyFactory(new
	// ClassPathResource("keystore_new.jks"), "password".toCharArray())
	// .getKeyPair("selfsigned_new");
	// converter.setKeyPair(keyPair);
	// return converter;
	// }
	//
	// @Bean
	// public JwtTokenStore jwtTokenStore(){
	// return new JwtTokenStore(jwtAccessTokenConverter());
	// }

	// @Override
	// public void configure(AuthorizationServerEndpointsConfigurer endpoints)
	// throws Exception {
	// endpoints.authenticationManager(authenticationManager);
	// }
	//
	// @Override
	// public void configure(ClientDetailsServiceConfigurer clients) throws
	// Exception {
	// clients
	// .inMemory()
	// .withClient("acme")
	// .secret("acmesecret")
	// .autoApprove(true)
	// .authorizedGrantTypes("authorization_code", "refresh_token", "password")
	// .accessTokenValiditySeconds(3)
	// .refreshTokenValiditySeconds(3)
	// .scopes("sso1", "sso2")
	// .and()
	// .withClient("acme2")
	// .secret("acmesecret2")
	// .autoApprove(true)
	// .authorizedGrantTypes("authorization_code", "refresh_token", "password")
	// .accessTokenValiditySeconds(3)
	// .refreshTokenValiditySeconds(3)
	// .scopes("sso1", "sso2");
	// ;
	// }
	// @Override
	// public void configure(AuthorizationServerEndpointsConfigurer endpoints)
	// throws Exception {
	// endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter());
	//
	// }
	// @Override
	// public void configure(AuthorizationServerSecurityConfigurer oauthServer)
	// throws Exception {
	// oauthServer.tokenKeyAccess("permitAll()")
	// .checkTokenAccess("isAuthenticated()");
	// }

}
