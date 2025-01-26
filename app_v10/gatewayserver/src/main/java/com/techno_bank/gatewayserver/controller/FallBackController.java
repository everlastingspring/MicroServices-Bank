package com.techno_bank.gatewayserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeoutException;

@RestController
public class FallBackController {

    Logger logger= LoggerFactory.getLogger(FallBackController.class);

    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport() {
        logger.debug("fallback triggered");
        return Mono.just("An error occurred. Please try after some time or contact support team!!!");
    }
}
