package com.muf.modules.workflow.assignment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("/create")
    public ResponseEntity<AssignmentResponse> createAssignment(@Valid @RequestBody CreateAssignmentRequest request ) {
        AssignmentResponse assignmentResponse =  assignmentService.createAssignment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentResponse);
    }

    @GetMapping
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsByEntity(@RequestParam String entityType, @RequestParam Integer entityId){
        List<AssignmentResponse> response = assignmentService.getAssignmentsByEntity(entityType, entityId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AssignmentResponse>> getAssignmentsByUuser(@PathVariable Integer userId){
        List<AssignmentResponse> response = assignmentService.getAssignmentsByUser(userId);
        return ResponseEntity.ok(response);
    }
}



















