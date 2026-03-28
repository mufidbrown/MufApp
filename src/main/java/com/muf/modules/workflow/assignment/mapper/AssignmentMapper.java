package com.muf.modules.workflow.assignment.mapper;

import com.muf.modules.master.user.entity.domain.User;
import com.muf.modules.master.user.repository.UserRepository;
import com.muf.modules.workflow.assignment.Assignment;
import com.muf.modules.workflow.assignment.AssignmentResponse;
import com.muf.modules.workflow.assignment.rules.AssignmentStageRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignmentMapper {

    private final AssignmentStageRules assignmentStageRules;
    private final UserRepository userRepository;



    public AssignmentResponse toAssignmentResponse(Assignment assignment) {
        AssignmentResponse response = new AssignmentResponse();
        response.setEntityType(assignment.getEntityType());
        response.setEntityId(assignment.getEntityId());
        response.setUserId(assignment.getUserId());

        // Get entity name
        String entityName = assignmentStageRules.getEntityName(assignment.getEntityType(), assignment.getEntityId());
        response.setEntityName(entityName);

        // Get user name
        String userName = userRepository.findById(assignment.getUserId())
                .map(User::getFullName)
                .orElse("Unknown");
        response.setUserName(userName);

        return response;
    }
}
