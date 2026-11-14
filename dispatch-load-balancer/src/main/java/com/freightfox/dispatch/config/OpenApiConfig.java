
package com.freightfox.dispatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("FreightFox Dispatch Load Balancer API")
                        .description(
                            "REST APIs for managing delivery orders, vehicles "
                            + "and generating optimized dispatch plans based "
                            + "on priority, vehicle capacity and travel distance."
                        )
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Dispatch Load Balancer")
                                .email("support@freightfox.ai")
                        )
                );
    }
}
