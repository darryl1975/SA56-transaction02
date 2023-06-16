package sg.edu.nus.iss.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Woosh woosh")
                        .description("my sample demo")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Darryl")
                                .url("http://localhost:4000")
                                .email("darryl1975@gmail.com"))
                        .termsOfService("TOC")
                        .license(new License().name("License").url("#")));
    }
}
