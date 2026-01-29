package com.muf.modules.master.user.dto;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserResponse {
    private Integer id;
    private String fullName;
    private String email;
    private List<String> roles;
    private Boolean isActive;
    private Boolean isLocked;
    private Integer loginAttempt;
    private Date loginAttemptDate;
    private Date logDate;
    private Date createdAt;
    private Date updatedAt;
}