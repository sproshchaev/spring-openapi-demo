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
                        .description("Демонстрационный Spring Boot микросервис для управления персонами с автоматической генерацией документации OpenAPI. " +
                                "Полный исходный код доступен в репозитории GitHub.")
                        .contact(new Contact()
                                .name("Sergey Proshchaev")
                                .email("sergey_proshchaev@it-architects.ai")
                                .url("https://github.com/sproshchaev/spring-openapi-demo"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
