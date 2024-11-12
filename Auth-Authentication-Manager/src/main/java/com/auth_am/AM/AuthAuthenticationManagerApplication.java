package com.auth_am.AM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthAuthenticationManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthAuthenticationManagerApplication.class, args);
	}

}
