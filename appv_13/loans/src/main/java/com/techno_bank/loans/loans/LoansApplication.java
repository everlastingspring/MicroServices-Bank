package com.techno_bank.loans.loans;

import com.techno_bank.loans.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
@EnableDiscoveryClient
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "TechnoBank Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "prashanth",
						email = "tutor@TechnoBank.com",
						url = "https://www.TechnoBank.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.TechnoBanks.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "TechnoBank Loans microservice REST API Documentation",
				url = "https://www.TechnoBank.com/swagger-ui.html"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
}
