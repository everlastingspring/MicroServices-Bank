package com.techno_bank.accounts.service;

import com.techno_bank.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber,String correlationId);
}
