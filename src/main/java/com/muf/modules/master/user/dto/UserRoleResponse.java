package com.muf.modules.master.user.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserRoleResponse {
    private Integer id;
    private Integer userId;
    private String userName;
    private Integer roleId;
    private String roleName;
    private Date startDate;
    private Date endDate;
    private Boolean isDefault;
    private Date createdAt;
    private Date updatedAt;


}
