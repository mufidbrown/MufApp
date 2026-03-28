package com.muf.modules.master.conversion;

import com.muf.base.exception.BusinessException;
import com.muf.common.exception.customconversion.LeadAlreadyConvertedException;
import com.muf.common.exception.customleadflow.LeadNotFoundException;
import com.muf.common.exception.customopportunity.OpportunityNotFoundException;
import com.muf.modules.master.contact.Contact;
import com.muf.modules.master.contact.ContactRepository;
import com.muf.modules.master.conversion.mapper.ConversionMapper;
import com.muf.modules.master.customeraccount.CustomerAccount;
import com.muf.modules.master.customeraccount.CustomerAccountRepository;
import com.muf.modules.master.lead.entity.domain.Lead;
import com.muf.modules.master.lead.entity.domain.LeadHistory;
import com.muf.modules.master.lead.repository.LeadHistoryRepository;
import com.muf.modules.master.lead.repository.LeadRepository;
import com.muf.modules.workflow.opportunity.Opportunity;
import com.muf.modules.workflow.opportunity.OpportunityRepository;
import com.muf.modules.workflow.opportunity.OpportunityStageHistory;
import com.muf.modules.workflow.opportunity.OpportunityStageHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversionServiceImpl implements ConversionService {

    private final LeadRepository leadRepository;
    private final CustomerAccountRepository customerAccountRepository;
    private final ContactRepository contactRepository;
    private final OpportunityRepository opportunityRepository;
    private final ConversionRepository conversionRepository;
    private final LeadHistoryRepository leadHistoryRepository;
    private final OpportunityStageHistoryRepository opportunityStageHistoryRepository;
    private final ConversionMapper conversionMapper;



    @Override
    @Transactional
    public ConversionResponse convertLead(Integer leadId, ConvertLeadRequest convertLeadRequest) {

        /*validation*/

        // validate lead exists
        Lead lead = leadRepository.findByIdWithDetails(leadId)
                .orElseThrow(() -> new LeadNotFoundException(leadId));

        // check if already converted (prevent double conversion)
        if (conversionRepository.existsByLeadId(leadId)) {
            throw new LeadAlreadyConvertedException(leadId);
        }

        // validate lead status (must be QUALIFIED or already CONVERTED)
        if (!"QUALIFIED".equals(lead.getStatus()) && !"CONVERTED".equals(lead.getStatus())) {
            throw new LeadNotFoundException(leadId);
        }

        // validate opportunity exists and belongs to this lead
        Opportunity opportunity = opportunityRepository.findById(convertLeadRequest.getOpportunityId())
                .orElseThrow(() -> new OpportunityNotFoundException(convertLeadRequest.getOpportunityId()));

        if (!opportunity.getLeadId().equals(leadId)) {
            throw new BusinessException("Opportunity " + convertLeadRequest.getOpportunityId() + " does not belong to lead " + leadId);
        }

        /*create customer account*/
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCompanyName(convertLeadRequest.getCompanyName());
        customerAccount.setIndustry(convertLeadRequest.getIndustry());
        customerAccount.setSize(convertLeadRequest.getSize());
        customerAccount.setAddress(convertLeadRequest.getAddress());
        customerAccount.setOwnerId(lead.getOwnerId());
        CustomerAccount savedCustomerAccount = customerAccountRepository.save(customerAccount);

        /*create contact*/
        Contact contact = new Contact();
        contact.setFullName(convertLeadRequest.getContactFullName());
        contact.setPhone(convertLeadRequest.getContactPhone());
        contact.setEmail(convertLeadRequest.getContactEmail());
        contact.setPosition(convertLeadRequest.getPosition());
        contact.setCustomerAccountId(savedCustomerAccount.getId());
        Contact savedContact = contactRepository.save(contact);

        /*create conversion record*/
        Conversion conversion = new Conversion();
        conversion.setLead(lead);
        conversion.setCustomerAccountId(savedCustomerAccount.getId());
        conversion.setContactId(savedContact.getId());
        conversion.setConvertedAt(LocalDateTime.now());
        conversionRepository.save(conversion);

        /*update lead status*/
        String oldLeadStatus = lead.getStatus();
        lead.setStatus("CONVERTED");
        leadRepository.save(lead);

        //create lead history
        LeadHistory leadHistory = new LeadHistory();
        leadHistory.setLeadId(leadId);
        leadHistory.setFromStatus(oldLeadStatus);
        leadHistory.setToStatus(oldLeadStatus);
        leadHistory.setChangedAt(LocalDateTime.now());
        leadHistoryRepository.save(leadHistory);

        /*update opportunity stage to won*/
        String oldOpportunityStage = opportunity.getStage();
        opportunity.setStage("WON");
        opportunity.setProbability(100);
        opportunityRepository.save(opportunity);

        // create opportunity stage history
        OpportunityStageHistory opportunityStageHistory = new OpportunityStageHistory();
        opportunityStageHistory.setOpportunityId(opportunity.getId());
        opportunityStageHistory.setFromStage(oldOpportunityStage);
        opportunityStageHistory.setToStage("WON");
        opportunityStageHistory.setChangedAt(LocalDateTime.now());
        opportunityStageHistoryRepository.save(opportunityStageHistory);


        // Map to Response
        return conversionMapper.toConversionResponse(conversion, lead, savedCustomerAccount, savedContact, opportunity);

    }

}
