package com.muf.modules.analytics.salesperformancereport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalesPerformanceServiceImpl implements SalesPerformanceService {

    private final SalesPerformanceRepository salesPerformanceRepository;

    @Override
    @Transactional(readOnly = true)
    public SalesPerformanceSummary getSalesPerformanceReport() {
        List<Map<String, Object>> rawData = salesPerformanceRepository.getSalesPerformanceReport();

        List<SalesPerformanceResponse> performances = rawData.stream()
                .map(this::mapToSalesPerformance)
                .collect(Collectors.toList());

        // calculate overall metrics
        SalesOverallMetrics overallMetrics = calculateOverallMetrics(performances);

        SalesPerformanceSummary salesPerformanceSummary = new SalesPerformanceSummary();
        salesPerformanceSummary.setSalesPerformance(performances);
        salesPerformanceSummary.setSalesOverallMetrics(overallMetrics);
        return salesPerformanceSummary;
    }

    private SalesPerformanceResponse mapToSalesPerformance(Map<String, Object> data) {
        SalesPerformanceResponse response = new SalesPerformanceResponse();
        response.setUserId(getIntegerValue(data, "userId"));
        response.setFullName(getStringValue(data, "fullName"));
        response.setTotalDeals(getIntegerValue(data, "totalDeals"));
        response.setTotalRevenue(getBigDecimalValue(data, "totalRevenue"));
        response.setWinRate(getDoubleValue(data, "winRate"));
        response.setWonDeals(getIntegerValue(data, "wonDeals"));
        response.setLostDeals(getIntegerValue(data, "lostDeals"));
        response.setAverageDealSize(getBigDecimalValue(data, "averageDealSize"));
        return response;
    }

    private SalesOverallMetrics calculateOverallMetrics(List<SalesPerformanceResponse> performances) {
        SalesOverallMetrics metrics = new SalesOverallMetrics();

        Integer totalDeals = performances.stream()
                .mapToInt(SalesPerformanceResponse::getTotalDeals)
                .sum();

        BigDecimal totalRevenue = performances.stream()
                .map(SalesPerformanceResponse::getTotalRevenue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Double averageWinRate = performances.stream()
                .mapToDouble(SalesPerformanceResponse::getWinRate)
                .average()
                .orElse(0.0);

        SalesPerformanceResponse topPerformer = performances.stream()
                .max(Comparator.comparing(SalesPerformanceResponse::getTotalRevenue))
                .orElse(null);

        metrics.setTotalDeals(totalDeals);
        metrics.setTotalRevenue(totalRevenue);
        metrics.setAverageWinRate(averageWinRate);
        metrics.setTotalSalesReps((int) performances.size());
        metrics.setTopPerformer(topPerformer);

        return metrics;
    }


    // Value extraction helpers

    private Integer getIntegerValue(Map<String, Object> data, String key) {
        Object value = data.get(key);
        if (value == null) return 0;
        if (value instanceof Number) return ((Number) value).intValue();
        return 0;
    }

    private Double getDoubleValue(Map<String, Object> data, String key) {
        Object value = data.get(key);
        if (value == null) return 0.0;
        if (value instanceof Number) return ((Number) value).doubleValue();
        return 0.0;
    }

    private BigDecimal getBigDecimalValue(Map<String, Object> data, String key) {
        Object value = data.get(key);
        if (value == null) return BigDecimal.ZERO;
        if (value instanceof BigDecimal) return (BigDecimal) value;
        if (value instanceof Number) return BigDecimal.valueOf(((Number) value).doubleValue());
        return BigDecimal.ZERO;
    }

    private String getStringValue(Map<String, Object> data, String key) {
        Object value = data.get(key);
        return value != null ? value.toString() : "";
    }
}
