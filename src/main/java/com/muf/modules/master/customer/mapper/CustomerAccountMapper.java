package com.muf.modules.master.customer.mapper;

import com.muf.modules.master.customer.CustomerAccount;
import com.muf.modules.master.customer.CustomerAccountResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerAccountMapper {

    public CustomerAccountResponse toCustomerAccountResponse(CustomerAccount customerAccount) {
        CustomerAccountResponse response = new CustomerAccountResponse();
        response.setId(customerAccount.getId());
        response.setCompanyName(customerAccount.getCompanyName());
        response.setIndustry(customerAccount.getIndustry());
        response.setSize(customerAccount.getSize());
        response.setAddress(customerAccount.getAddress());
        response.setOwnerId(customerAccount.getOwnerId());
        response.setCreatedAt(customerAccount.getCreatedAt());
        response.setUpdatedAt(customerAccount.getUpdatedAt());

//        if (ca.getOwner() != null) {
//            response.setOwnerName(ca.getOwner().getName());
//        }
//
//        // Get counts
//        response.setTotalContacts(contactRepository.findByCustomerAccountId(ca.getId()).size());

        return response;
    }
}
