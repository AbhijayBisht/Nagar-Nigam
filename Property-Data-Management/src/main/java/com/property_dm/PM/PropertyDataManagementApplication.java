package com.property_dm.PM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableJpaRepositories(basePackages = "com.property_dm.PM.repositories")
public class PropertyDataManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyDataManagementApplication.class, args);
	}

}
