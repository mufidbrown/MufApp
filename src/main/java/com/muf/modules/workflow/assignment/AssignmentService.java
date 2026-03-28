package com.muf.modules.workflow.assignment;


import java.util.List;

public interface AssignmentService {
    AssignmentResponse createAssignment(CreateAssignmentRequest request);
    List<AssignmentResponse> getAssignmentsByEntity(String entityType, Integer entityId);
    List<AssignmentResponse> getAssignmentsByUser(Integer userId);
}
