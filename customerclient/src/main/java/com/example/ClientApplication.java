package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableOAuth2Sso
@SpringBootApplication
//@EnableDiscoveryClient
//@RefreshScope
//@EnableCircuitBreaker
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

}