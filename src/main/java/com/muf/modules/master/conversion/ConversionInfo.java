package com.muf.modules.master.conversion;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConversionInfo {
    private Integer leadId;
    private String leadName;
    private LocalDateTime convertedAt;
}
