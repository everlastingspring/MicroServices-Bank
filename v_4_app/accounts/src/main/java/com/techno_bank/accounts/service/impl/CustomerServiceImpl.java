package com.techno_bank.accounts.service.impl;

import com.techno_bank.accounts.dto.*;
import com.techno_bank.accounts.entity.Account;
import com.techno_bank.accounts.entity.Customer;
import com.techno_bank.accounts.excpetion.ResourceNotFoundException;
import com.techno_bank.accounts.mapper.AccountMapper;
import com.techno_bank.accounts.mapper.CustomerMapper;
import com.techno_bank.accounts.repository.AccountsRepository;
import com.techno_bank.accounts.repository.CustomerRepository;
import com.techno_bank.accounts.service.ICustomerService;
import com.techno_bank.accounts.service.client.CardsFeignClient;
import com.techno_bank.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private LoansFeignClient loansFeignClient;
    private CardsFeignClient cardsFeignClient;


    /**
     * @param mobileNumber
     * @return customerDto
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Account account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapToAccountsDto(account,new AccountDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());


        return customerDetailsDto;
    }
}
