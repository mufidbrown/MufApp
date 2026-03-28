package com.muf.modules.analytics.salesperformancereport;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class SalesPerformanceController {

    private final SalesPerformanceService salesPerformanceService;

    @GetMapping("/sales-performance")
    public ResponseEntity<SalesPerformanceSummary> getSalesPerformanceReport() {
        SalesPerformanceSummary response = salesPerformanceService.getSalesPerformanceReport();
        return ResponseEntity.ok(response);
    }
}
