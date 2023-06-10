package com.example.simpleproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Market Place",
                description = "Bu project Market uchun",
                contact = @Contact(name = "Sayriddin Ergashev"
                ,url = "https://www.youtube.com/",
                        email = "sayriddinergashev77@gmail.com "),
license = @License(
        name = "license",
        url = "https://www.youtube.com/"
),
                version = "3.0.2 Version"
        ),
        tags = @Tag(
                name = "Market Place",
                description = "Tag description"
        ),
        servers = @Server(
                url = "localhost:8082",
                description = "simple swagger project"
        )

)

public class SimpleProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleProjectApplication.class, args);
    }

}
