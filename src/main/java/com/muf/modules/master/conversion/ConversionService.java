package com.muf.modules.master.conversion;

public interface ConversionService {
    ConversionResponse convertLead(Integer leadId, ConvertLeadRequest request);
//    ConversionDetailResponse getConversionDetails(Integer leadId);
}
