package com.tax_dm.TM.config;

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
				title = "Tax-Records Management API",
				description = "Doing CRUD Operations",
				summary = "This Tax-api will do Create, Read, Upadte and Delete operations",
				termsOfService = "T&C",
				contact = @Contact(
						name = "Abhijay Bisht",
						email = "abhijay.bisht09@gmail.com"),
				version = "v1"
			),
		servers = {
				@Server(
						description = "dev",
						url = "http://localhost:8083"				
				),
				@Server(
						description = "test",
						url = "http://localhost:8083"				
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
