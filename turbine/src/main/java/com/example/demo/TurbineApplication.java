package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableTurbine
public class TurbineApplication {

//	call hystrix dashboard via http://localhost:6565/hystrix
// 	type in: http://192.168.99.101:9999/client/hystrix.stream in the first input field and
//	tpye in: CLIENT in the second input field
	public static void main(String[] args) {
		SpringApplication.run(TurbineApplication.class, args);
	}

}
