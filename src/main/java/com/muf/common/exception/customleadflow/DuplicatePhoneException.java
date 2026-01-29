package com.muf.common.exception.customleadflow;

import com.muf.base.exception.BusinessException;

public class DuplicatePhoneException extends BusinessException {
    public DuplicatePhoneException(String phone) {
        super("Phone number already exists: " + phone);
    }
}
