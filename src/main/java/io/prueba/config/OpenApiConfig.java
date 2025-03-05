package io.prueba.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(@Value("${valor.openapi.title}") String title,
                                 @Value("${valor.openapi.version}") String version,
                                 @Value("${valor.openapi.description}") String description){
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description(description));
    }
}
