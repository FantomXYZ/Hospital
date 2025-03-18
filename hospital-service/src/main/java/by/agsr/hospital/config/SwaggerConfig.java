package by.agsr.hospital.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.Scopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String OAUTH2_SCHEME = "oauth2";

    @Value("${spring.security.oauth2.resource-server.jwt.issuer-uri}")
    private String issuerUri;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Patients API").version("1.0").description("CRUD methods of Patients API"))
                .addSecurityItem(new SecurityRequirement().addList(OAUTH2_SCHEME))
                .components(new Components().addSecuritySchemes(OAUTH2_SCHEME, new SecurityScheme()
                                .name(OAUTH2_SCHEME)
                                .type(SecurityScheme.Type.OAUTH2)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .flows(new OAuthFlows().password(new OAuthFlow()
                                        .tokenUrl(issuerUri + "/protocol/openid-connect/token")
                                        .scopes(new Scopes())))));
    }
}
