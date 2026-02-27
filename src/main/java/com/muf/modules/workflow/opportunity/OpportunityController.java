package com.muf.modules.workflow.opportunity;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/opportunities")
@RequiredArgsConstructor
public class OpportunityController {

    private final OpportunityService opportunityService;

    @PostMapping("/create")
    public ResponseEntity<OpportunityResponse> createOpportunity(@Valid @RequestBody CreateOpportunityRequest request) {
        OpportunityResponse opportunityResponse = opportunityService.createOpportunity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(opportunityResponse);
    }
}
