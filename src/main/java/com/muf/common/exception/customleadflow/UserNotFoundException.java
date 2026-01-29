package com.muf.common.exception.customleadflow;

import com.muf.base.exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(Integer id) {
        super("User not found with id: " + id);
    }
}
