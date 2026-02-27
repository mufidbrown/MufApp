package com.muf.modules.workflow.opportunity.validator;

import com.muf.common.exception.customopportunity.InvalidProbabilityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpportunityValidator {

    public void validateProbability(Integer probability) {
        if (probability < 0 || probability > 100) {
            throw new InvalidProbabilityException(probability);
        }
    }

}
