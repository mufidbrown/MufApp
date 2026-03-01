package com.muf.common.exception.customcustomercontact;

import com.muf.base.exception.BusinessException;

public class ContactNotFoundException extends BusinessException {
    public ContactNotFoundException(Integer id) {
        super("Contact not found with id: " + id);
    }
}
