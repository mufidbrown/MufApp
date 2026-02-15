package com.muf.modules.workflow.task;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tasks")
public class Task extends TaskEntity{
}
