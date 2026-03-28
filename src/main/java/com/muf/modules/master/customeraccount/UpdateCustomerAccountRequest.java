package com.muf.modules.master.customeraccount;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCustomerAccountRequest {
    @Size(max = 150, message = "Company name must not exceed 150 characters")
    private String companyName;

    @Size(max = 100, message = "Industry must not exceed 100 characters")
    private String industry;

    @Size(max = 50, message = "Size must not exceed 50 characters")
    private String size;

    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;

    private Integer ownerId;
}

