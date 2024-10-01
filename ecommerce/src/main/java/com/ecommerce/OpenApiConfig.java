package com.ecommerce;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Repository",
                        url = "https://github.com/noeldmb/ecommerce/tree/main"
                ),
                description = "E-commerce, Online shopping.",
                title = "E-commerce",
                version = "1.1"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"

                )
        }
)
public class OpenApiConfig {
}
