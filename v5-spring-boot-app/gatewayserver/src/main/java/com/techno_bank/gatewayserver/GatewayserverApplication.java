package com.techno_bank.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator technoBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(path -> path
                        .path("/technobank/accounts/**")
                        .filters(filter -> filter.rewritePath("/technobank/accounts/(?<remaining>.*)", "/${remaining}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://ACCOUNTS"))
                .route(path -> path
                        .path("/technobank/cards/**")
                        .filters(filter -> filter.rewritePath("/technobank/cards/(?<remaining>.*)", "/${remaining}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://CARDS"))
                .route(path -> path
                        .path("/technobank/loans/**")
                        .filters(filter -> filter.rewritePath("/technobank/loans/(?<remaining>.*)", "/${remaining}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://LOANS"))
                .build();
    }
}
