package com.ashish.userservice.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI custumOpenAPI() {

		return new OpenAPI().info(

				new Info().title("E-Commerce API Document")
						.description("REST API documentation for the E-Commerce User Service.").version("v1.0.0")

						.contact(new Contact().name("Ashish Kumar").email("kuashish156@gmail.com"))

						.license(new License().name("Internal Api"))

		);
	}
}
