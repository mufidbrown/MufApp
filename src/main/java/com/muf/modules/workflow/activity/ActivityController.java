package com.muf.modules.workflow.activity;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/create")
    public ResponseEntity<ActivityResponse> createActivity(@Valid @RequestBody CreateActivityRequest request){
        ActivityResponse response = activityService.createActivity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponse> updateActivity(@PathVariable Integer id, @Valid @RequestBody UpdateActivityRequest request){
        ActivityResponse response = activityService.updateActivity(id, request);
        return ResponseEntity.ok(response);
    }
}
