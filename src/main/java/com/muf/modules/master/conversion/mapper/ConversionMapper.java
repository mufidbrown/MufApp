package com.muf.modules.master.conversion.mapper;

import com.muf.modules.master.contact.Contact;
import com.muf.modules.master.conversion.Conversion;
import com.muf.modules.master.conversion.ConversionResponse;
import com.muf.modules.master.customeraccount.CustomerAccount;
import com.muf.modules.master.lead.entity.domain.Lead;
import com.muf.modules.workflow.opportunity.Opportunity;
import org.springframework.stereotype.Component;

@Component
public class ConversionMapper {

    public ConversionResponse toConversionResponse(
            Conversion conversion,
            Lead lead,
            CustomerAccount savedAccount,
            Contact savedContact,
            Opportunity opportunity
    ) {
        ConversionResponse conversionResponse = new ConversionResponse();
//        conversionResponse.setLeadId(conversion.getLeadId());
        conversionResponse.setLeadId(conversion.getLead().getId());
        conversionResponse.setLeadName(lead.getName());
        conversionResponse.setLeadStatus(lead.getStatus());
        conversionResponse.setAccountId(savedAccount.getId());
        conversionResponse.setCompanyName(savedAccount.getCompanyName());
        conversionResponse.setContactId(savedContact.getId());
        conversionResponse.setContactFullName(savedContact.getFullName());
        conversionResponse.setOpportunityId(opportunity.getId());
        conversionResponse.setOpportunityStage(opportunity.getStage());
        conversionResponse.setConvertedAt(conversion.getConvertedAt());

        return conversionResponse;
    }
}
