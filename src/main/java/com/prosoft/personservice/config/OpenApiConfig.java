package com.prosoft.personservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Person Service API")
                        .version("1.0.0")
                        .description("Демонстрационный микросервис для управления персонами с автоматической документацией OpenAPI")
                        .contact(new Contact()
                                .name("Команда разработки")
                                .email("dev@example.com")
                                .url("https://github.com/alexey-techpisarov/person-service-openapi-demo"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}