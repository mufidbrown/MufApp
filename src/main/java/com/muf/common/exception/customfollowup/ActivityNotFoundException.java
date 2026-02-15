package com.muf.common.exception.customfollowup;

import com.muf.base.exception.BusinessException;

public class ActivityNotFoundException extends BusinessException {
    public ActivityNotFoundException(Integer id) {
        super("Activity not found with id: " + id);
    }
}
