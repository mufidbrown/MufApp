package com.muf.infrastructure.scheduler.workflowactivityfollowup;


import com.muf.modules.workflow.reminder.Reminder;
import com.muf.modules.workflow.reminder.ReminderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReminderScheduler {

    private final ReminderRepository reminderRepository;

    /**
     * Check for pending reminders every 5 minutes
     * This prevents follow-ups from being forgotten
     */
    // @Scheduled(cron = "0 */5 * * * *") // Every 5 minutes
    @Scheduled(cron = "*/10 * * * * *") // tiap 10 detik
    @Transactional
    public void processPendingReminders() {
        List<Reminder> pendingReminders = reminderRepository.findPendingReminders(LocalDateTime.now());

        if (!pendingReminders.isEmpty()) {
            log.info("Processing {} pending reminders", pendingReminders.size());

            for (Reminder reminder : pendingReminders) {
                try {
                    // TODO: Send notification (email, push, etc.)
                    // For now, just update status
                    reminder.setStatus("SENT");
                    reminderRepository.save(reminder);

                    log.info("Reminder sent for {} ID: {}",
                            reminder.getEntityType(),
                            reminder.getEntityId());
                } catch (Exception e) {
                    log.error("Failed to process reminder ID: {}", reminder.getId(), e);
                }
            }
        }
    }
}
