package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
public class EventClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventClientApplication.class, args);
	}

}