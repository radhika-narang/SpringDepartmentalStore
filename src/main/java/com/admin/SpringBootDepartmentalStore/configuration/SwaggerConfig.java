package com.admin.SpringBootDepartmentalStore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.GroupedOpenApi;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Departmental Store API",
                version = "1.0",
                description = "API documentation for Departmental Store CRUD application"
        ),
        servers = {
                @Server(url = "http://localhost:9111", description = "Local Server")
        }
)
public class SwaggerConfig implements WebMvcConfigurer {



        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/swagger-ui/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/");
        }

}
