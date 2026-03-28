package com.muf.modules.master.conversion;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leads")
@RequiredArgsConstructor
public class ConversionController {

    private final ConversionService conversionService;

    @PostMapping("/{id}/convert")
    public ResponseEntity<ConversionResponse> convertLead(@PathVariable Integer id, @Valid @RequestBody ConvertLeadRequest convertLeadRequest) {
        ConversionResponse conversionResponse = conversionService.convertLead(id, convertLeadRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(conversionResponse);
    }
}
