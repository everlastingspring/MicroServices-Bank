package com.techno_bank.accounts.service.client;

import com.techno_bank.accounts.dto.CardsDto;
import com.techno_bank.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans",url = "http://loans:8090",fallback = LoansFallBack.class)
public interface LoansFeignClient {

    @GetMapping(value = "api/fetch", consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("technobank-correlation-id") String correlationId, @RequestParam String mobileNumber);
}
