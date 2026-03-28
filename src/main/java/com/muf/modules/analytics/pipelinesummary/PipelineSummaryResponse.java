package com.muf.modules.analytics.pipelinesummary;

import lombok.Data;
import org.apache.catalina.Pipeline;

import java.util.List;

@Data
public class PipelineSummaryResponse {
    private List<PipelineStageResponse> stages;
    private PipelineOverallMetrics overallMetrics;
}
