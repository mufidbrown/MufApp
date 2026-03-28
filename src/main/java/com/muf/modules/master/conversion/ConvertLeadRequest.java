package com.muf.modules.master.conversion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ConvertLeadRequest {

    @NotBlank(message = "Company name is required")
    @Size(max = 150, message = "Company name must not exceed 150 characters")
    private String companyName;

    @Size(max = 100, message = "Industry must not exceed 100 characters")
    private String industry;

    @Size(max = 50, message = "Size must not exceed 50 characters")
    private String size;

    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;

    // Contact information (usually from lead data, but can be overridden)
    @NotBlank(message = "Contact full name is required")
    @Size(max = 100, message = "Full name must not exceed 100 characters")
    private String contactFullName;

    @Size(max = 30, message = "Phone must not exceed 30 characters")
    private String contactPhone;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String contactEmail;

    @Size(max = 100, message = "Position must not exceed 100 characters")
    private String position;

    // Opportunity ID (must exist and belong to this lead)
    @NotNull(message = "Opportunity ID is required")
    private Integer opportunityId;

}
