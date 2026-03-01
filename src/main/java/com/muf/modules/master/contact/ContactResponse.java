package com.muf.modules.master.contact;

import lombok.Data;

import java.util.Date;

@Data
public class ContactResponse {
    private Integer id;
    private String fullName;
    private String phone;
    private String email;
    private String position;
    private Integer customerAccountId;
    private String customerAccountName;
    private Date createdAt;
    private Date updatedAt;
}
