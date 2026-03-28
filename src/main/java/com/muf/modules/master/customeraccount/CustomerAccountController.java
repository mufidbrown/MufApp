package com.muf.modules.master.customeraccount;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer-accounts")
@RequiredArgsConstructor
public class CustomerAccountController {

    private final CustomerAccountService customerAccountService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public ResponseEntity<CustomerAccountResponse> createCustomerAccount(@Valid @RequestBody CreateCustomerAccountRequest request) {
        CustomerAccountResponse response = customerAccountService.createCustomerAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
