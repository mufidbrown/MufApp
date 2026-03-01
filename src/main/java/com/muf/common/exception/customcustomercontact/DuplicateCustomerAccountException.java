package com.muf.common.exception.customcustomercontact;

import com.muf.base.exception.BusinessException;

public class DuplicateCustomerAccountException extends BusinessException {
    public DuplicateCustomerAccountException(String companyName) {
        super("Customer account already exists with company name: " + companyName);
    }
}
