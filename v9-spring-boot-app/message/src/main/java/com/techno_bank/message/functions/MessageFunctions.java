package com.techno_bank.message.functions;

import com.techno_bank.message.dto.AccountsMessageDto;
import com.techno_bank.message.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    @Autowired
    private EmailService emailService;

    @Autowired
    private StreamBridge streamBridge;


    private static final Logger LOGGER = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<AccountsMessageDto, AccountsMessageDto> email() {
        return accountsMsgDto -> {
        LOGGER.info("Sending email with the details : {}", accountsMsgDto.toString());
        String subject = "Account Created Successfully";
        String text = "Dear " + accountsMsgDto.name() + ",\n\nYour account has been created successfully!";
        emailService.sendEmail(accountsMsgDto.email(), subject, text);

        // Optionally, send a message to another destination
        streamBridge.send("email-out-0", accountsMsgDto.accountNumber());
        return accountsMsgDto;
    };
}

//    @Bean
//    public Function<AccountsMessageDto, Long> sms() {
//        return accountsMsgDto -> {
//            LOGGER.info("Sending sms with the details : {}", accountsMsgDto.toString());
//            return accountsMsgDto.accountNumber();
//        };
//    }
}
