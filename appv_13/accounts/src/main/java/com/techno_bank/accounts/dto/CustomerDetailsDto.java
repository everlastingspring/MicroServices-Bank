package com.techno_bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDetails",
        description = "Schema to hold Customers, Accounts, Loans, Cards information"
)
public class CustomerDetailsDto {

    @Schema(
            description = "Name of the customer", example = "Techno bytes"
    )
    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "cust@technobank.com"
    )
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(regexp = ".+[@].+[\\.].+", message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer", example = "refer accountDto"
    )
    private AccountDto accountDto;

    @Schema(
            description = "Loans details of the Customer", example = "refer loansDto"
    )
    private LoansDto loansDto;

    @Schema(
            description = "Cards details of the Customer", example = "refer cardsDto"
    )
    private CardsDto cardsDto;
}
