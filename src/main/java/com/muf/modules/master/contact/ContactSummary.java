package com.muf.modules.master.contact;

import lombok.Data;

@Data
public class ContactSummary {
    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private String position;
}
