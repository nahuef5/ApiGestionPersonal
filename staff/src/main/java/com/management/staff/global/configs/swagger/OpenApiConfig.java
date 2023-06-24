package com.management.staff.global.configs.swagger;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.*;
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Staff Management API",
                version = "V1.0",
                contact = @Contact(
                        name = "Nahuel", email = "nahuelejemplo@email.com", url = "https://www.nahuelf.com/"
                ),
                license = @License(
                        name = "License", url = "https://www.license2-0.com/"
                ),
                termsOfService = "${tos.uri}",
                description = "Employee management application."
        ),
        servers = {
            @Server(
                    url = "http://localhost:5050/",
                    description = "Development")
        }
)
public class OpenApiConfig{
    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "Bearer Authentication";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .description(
                                 "Provide the JWT token. JWT token can be obtained from the Login API."
                                )
                                .bearerFormat("JWT")));
    }
}