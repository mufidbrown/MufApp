package com.muf.modules.master.customer;

import com.muf.modules.master.contact.ContactSummary;
import com.muf.modules.master.conversion.ConversionInfo;
import com.muf.modules.workflow.opportunity.OpportunitySummary;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerAccountDetailResponse {
    private Integer id;
    private String companyName;
    private String industry;
    private String size;
    private String address;
    private Integer ownerId;
    private String ownerName;
    private List<ContactSummary> contacts;
    private List<OpportunitySummary> opportunities;
    private ConversionInfo conversionInfo;
    private Date createdAt;
    private Date updatedAt;
}
