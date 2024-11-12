package com.property_dm.PM.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
		info = @Info(
				title = "Property API",
				description = "Doing CRUD Operations",
				summary = "This Property-api will do Create, Read, Upadte and Delete operations",
				termsOfService = "T&C",
				contact = @Contact(
						name = "Abhijay Bisht",
						email = "abhijay.bisht09@gmail.com"),
				version = "v1"
			),
		servers = {
				@Server(
						description = "dev",
						url = "http://localhost:8081"				
				),
				@Server(
						description = "test",
						url = "http://localhost:8081"				
				)
		},
		security = @SecurityRequirement(
				name = "AuthSecurity"
		)
)


@SecurityScheme(
		name = "AuthSecurity",
		in = SecuritySchemeIn.HEADER,
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer",
		description = "Security Desciption"
)
public class SwaggerConfig {

}
