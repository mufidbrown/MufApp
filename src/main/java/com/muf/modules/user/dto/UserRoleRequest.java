package com.muf.modules.user.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserRoleRequest {
    private Integer userId;
    private Integer roleId;
    private Date startDate;
    private Date endDate;
    private Boolean isDefault;
}
