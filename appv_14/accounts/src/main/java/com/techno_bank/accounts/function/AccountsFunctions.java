package com.techno_bank.accounts.function;

import com.techno_bank.accounts.dto.AccountsMessageDto;
import com.techno_bank.accounts.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountService iAccountService){
        return accountNumber -> {
            LOGGER.info("Updating Communication status for the account number : " + accountNumber);
            iAccountService.updateCommunicationStatus(accountNumber);
        };
    }
}
