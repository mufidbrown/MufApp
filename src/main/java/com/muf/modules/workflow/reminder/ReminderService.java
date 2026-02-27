package com.muf.modules.workflow.reminder;

public interface ReminderService {
    ReminderResponse createReminder(CreateReminderRequest request);
    ReminderResponse updateReminder(Integer id, UpdateReminderRequest request);
}
