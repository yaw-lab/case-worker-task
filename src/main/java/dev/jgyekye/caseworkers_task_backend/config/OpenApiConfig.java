
package dev.jgyekye.caseworkers_task_backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Case Worker Task Management API",
                version = "1.0",
                description = "API documentation for the Case Worker Task Management Backend application.",
                contact = @Contact(
                        name = "Your Name/Team",
                        email = "your.email@example.com",
                        url = "https://github.com/your-repo"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Development Server"),
                // Add more servers for different environments (e.g., test, production)
                @Server(url = "https://api.yourdomain.com", description = "Production Server")
        }
)
@Configuration
public class OpenApiConfig {
    // This class is empty, but the annotations define the OpenAPI documentation details.
}