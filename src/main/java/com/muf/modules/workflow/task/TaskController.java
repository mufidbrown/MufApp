package com.muf.modules.workflow.task;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * STEP 4.1 — CREATE TASK
     * POST /tasks
     */
    @PostMapping("/create")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        TaskResponse response = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Integer id, @Valid @RequestBody UpdateTaskRequest request) {
        TaskResponse response = taskService.updateTask(id, request);
        return ResponseEntity.ok(response);
    }

    /*Update Task Status*/
    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable Integer id, @Valid @RequestBody UpdateTaskStatusRequest request) {
        TaskResponse response = taskService.updateTaskStatus(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Integer id) {
        TaskResponse response = taskService.getTaskById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskResponse>> getTaskByAssignedUser(@PathVariable Integer userId) {
        List<TaskResponse> response = taskService.getTaskByAssignedUser(userId);
        return ResponseEntity.ok(response);
    }

}
