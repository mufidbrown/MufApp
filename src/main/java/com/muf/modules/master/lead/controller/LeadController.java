package com.muf.modules.master.lead.controller;

import com.muf.modules.master.lead.dto.*;
import com.muf.modules.master.lead.service.LeadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/leads")
@RequiredArgsConstructor
public class LeadController {

    private final LeadService leadService;

    /*STEP 1.1 - CREATE LEAD*/
    @PostMapping("/create")
    public ResponseEntity<LeadResponse> createLead(@Valid @RequestBody CreateLeadRequest request){
        LeadResponse response = leadService.createLead(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*STEP 1.2 - ASSIGN LEAD*/
    @PostMapping("/{id}/assign")
    public ResponseEntity<LeadResponse> assignLead(@PathVariable Integer id, @Valid @RequestBody AssignLeadRequest request){
        LeadResponse response = leadService.assignLead(id, request);
        return ResponseEntity.ok(response);
    }

    /*STEP 1.3 - UPDATE STATUS LEAD*/
    @PatchMapping("/{id}/status")
    public ResponseEntity<LeadResponse> updateLeadStatus(@PathVariable Integer id, @Valid @RequestBody UpdateLeadStatusRequest request){
        LeadResponse response = leadService.updateLeadStatus(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadResponse> getLeadById(@PathVariable Integer id){
        LeadResponse response = leadService.getLeadById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<LeadHistoryResponse>> getLeadHistory(@PathVariable Integer id) {
        List<LeadHistoryResponse> response = leadService.getLeadHistory(id);
        return ResponseEntity.ok(response);
    }

}
