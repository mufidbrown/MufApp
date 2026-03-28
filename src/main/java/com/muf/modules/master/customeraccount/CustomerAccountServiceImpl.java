package com.muf.modules.master.customeraccount;

import com.muf.common.exception.customcustomercontact.CustomerAccountNotFoundException;
import com.muf.common.exception.customcustomercontact.DuplicateCustomerAccountException;
import com.muf.common.exception.customleadflow.UserNotFoundException;
import com.muf.modules.master.customeraccount.mapper.CustomerAccountMapper;
import com.muf.modules.master.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private final CustomerAccountRepository customerAccountRepository;
    private final UserRepository userRepository;
    private final CustomerAccountMapper customerAccountMapper;

    @Override
    @Transactional
    public CustomerAccountResponse createCustomerAccount(CreateCustomerAccountRequest request) {
        log.info("Creating customer account: {}", request.getCompanyName());

        // check for duplipicate company name (case-insensitive_
        if (customerAccountRepository.existsByCompanyNameIgnoreCase(request.getCompanyName())) {
            throw new DuplicateCustomerAccountException(request.getCompanyName());
        }

        // validate owner if provided
        if (request.getOwnerId() != null) {
            userRepository.findById(request.getOwnerId())
                    .orElseThrow(() -> new UserNotFoundException(request.getOwnerId()));
        }

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCompanyName(request.getCompanyName());
        customerAccount.setIndustry(request.getIndustry());
        customerAccount.setSize(request.getSize());
        customerAccount.setAddress(request.getAddress());
        customerAccount.setOwnerId(request.getOwnerId());
        customerAccount.setCreatedAt(new Date());

        CustomerAccount saved = customerAccountRepository.save(customerAccount);

        log.info(" Customer account created with ID: {}", saved.getId());

        // reload with owner
        CustomerAccount withOwner = customerAccountRepository.findByIdWithOwner(saved.getId())
                .orElseThrow(() -> new CustomerAccountNotFoundException(saved.getId()));

        return customerAccountMapper.toCustomerAccountResponse(withOwner);
    }
}
