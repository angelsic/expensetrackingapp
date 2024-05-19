package com.project.expensetrackingapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Expense Tracking App",
                version = "1.0.0",
                description = "CRUD Backend developed to keep track of Income and Expenses"
        )
)
public class OpenApiConfig {

        @Bean
        public OpenAPI customOpenApi(){
                final String securitySchemeName = "bearerAuth";
                return new OpenAPI()
                        .info(new Info().title("Authentication Service")
                                .description("This is auth service use for validate the user.")
                                .version("v1.0.0")
                                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                        .externalDocs(new ExternalDocumentation()
                                .description("SpringBoot Wiki Documentation")
                                .url("https://springboot.wiki.github.org/docs"))
                        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                        .components(
                                new Components()
                                        .addSecuritySchemes(securitySchemeName,
                                                new SecurityScheme()
                                                        .name(securitySchemeName)
                                                        .type(SecurityScheme.Type.HTTP)
                                                        .scheme("bearer")
                                                        .bearerFormat("JWT")
                                        )
                        );
        }

}
