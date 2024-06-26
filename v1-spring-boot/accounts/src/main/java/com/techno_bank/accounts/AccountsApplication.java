package com.techno_bank.accounts;

import com.techno_bank.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Microservice Rest Api Documentation",
                description = "Techno Bank Accounts microservice Rest Api Docs",
                version = "v1",
                contact = @Contact(
                        name = "prashanth",
                        email = "prashanth@bank.com",
                        url = "https://springdoc.org"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://springdoc.org"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "external doc site",
                url = "http://docurl.com"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
