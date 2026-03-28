package com.muf.modules.master.customeraccount;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerAccountResponse {
    private Integer id;
    private String companyName;
    private String industry;
    private String size;
    private String address;
    private Integer ownerId;
    private String ownerName;
    private Integer totalContacts;
    private Integer totalOpportunities;
    private Date createdAt;
    private Date updatedAt;
}
