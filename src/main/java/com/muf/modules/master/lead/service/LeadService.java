package com.muf.modules.master.lead.service;

import com.muf.modules.master.lead.dto.*;

import java.util.List;

public interface LeadService {

    LeadResponse createLead(CreateLeadRequest request);
//    LeadResponse assignLead(Integer leadId, AssignLeadRequest request);
//    LeadResponse updateLeadStatus(Integer leadId, UpdateLeadStatusRequest request);
//    LeadResponse getLeadById(Integer leadId);
//    List<LeadHistoryResponse> getLeadHistory(Integer leadId);

}
