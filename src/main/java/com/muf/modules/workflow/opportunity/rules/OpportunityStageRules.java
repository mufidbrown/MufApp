package com.muf.modules.workflow.opportunity.rules;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OpportunityStageRules {

    // Valid stage transitions (Pipeline moves forward)
    public static final Map<String, List<String>> VALID_STAGE_TRANSITIONS = new HashMap<>();

    // Stage probability mapping
    public static final Map<String, Integer> DEFAULT_PROBABILITY = new HashMap<>();

    static {
        // Define valid forward transitions
        VALID_STAGE_TRANSITIONS.put("PROSPECTING", Arrays.asList("QUALIFICATION", "LOST"));
        VALID_STAGE_TRANSITIONS.put("QUALIFICATION", Arrays.asList("PROPOSAL", "LOST"));
        VALID_STAGE_TRANSITIONS.put("PROPOSAL", Arrays.asList("NEGOTIATION", "LOST"));
        VALID_STAGE_TRANSITIONS.put("NEGOTIATION", Arrays.asList("WON", "LOST"));
        VALID_STAGE_TRANSITIONS.put("WON", Arrays.asList()); // Final state
        VALID_STAGE_TRANSITIONS.put("LOST", Arrays.asList()); // Final state

        // Default probability for each stage
        DEFAULT_PROBABILITY.put("PROSPECTING", 10);
        DEFAULT_PROBABILITY.put("QUALIFICATION", 25);
        DEFAULT_PROBABILITY.put("PROPOSAL", 50);
        DEFAULT_PROBABILITY.put("NEGOTIATION", 75);
        DEFAULT_PROBABILITY.put("WON", 100);
        DEFAULT_PROBABILITY.put("LOST", 0);
    }


    public boolean isValidStageTransition(String fromStage, String toStage) {
        if (fromStage.equals(toStage)) {
            return false; // Same stage
        }

        List<String> allowedTransitions = VALID_STAGE_TRANSITIONS.get(fromStage);
        return allowedTransitions != null && allowedTransitions.contains(toStage);
    }

    public boolean isFinalStage(String stage) {
        return "WON".equals(stage) || "LOST".equals(stage);
    }

}
