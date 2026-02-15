package com.muf.modules.workflow.reminder;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "reminders")
public class Reminder extends ReminderEntity {
}
