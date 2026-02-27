package com.muf.modules.workflow.reminder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    @PostMapping("/create")
    public ResponseEntity<ReminderResponse> createReminder(@Valid @RequestBody CreateReminderRequest request) {
        ReminderResponse response = reminderService.createReminder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReminderResponse> updateReminder(@PathVariable Integer id, @Valid @RequestBody UpdateReminderRequest request) {
        ReminderResponse response = reminderService.updateReminder(id, request);
        return ResponseEntity.ok(response);
    }
}
