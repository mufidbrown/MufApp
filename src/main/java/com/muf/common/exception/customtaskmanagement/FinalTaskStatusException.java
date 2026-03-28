package com.muf.common.exception.customtaskmanagement;


import com.muf.base.exception.BusinessException;

public class FinalTaskStatusException extends BusinessException {
    public FinalTaskStatusException(String status) {
        super("Cannot change status from final state: " + status);
    }
}

