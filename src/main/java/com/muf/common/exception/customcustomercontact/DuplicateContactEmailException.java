package com.muf.common.exception.customcustomercontact;

import com.muf.base.exception.BusinessException;

public class DuplicateContactEmailException extends BusinessException {
    public DuplicateContactEmailException(String email) {
        super("Contact already exists with email: " + email);
    }
}
