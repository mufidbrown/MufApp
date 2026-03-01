package com.muf.common.exception.customcustomercontact;

import com.muf.base.exception.BusinessException;

public class CustomerAccountNotFoundException extends BusinessException {
    public CustomerAccountNotFoundException(Integer id) {
        super("Customer account not found with id: " + id);
    }

}
