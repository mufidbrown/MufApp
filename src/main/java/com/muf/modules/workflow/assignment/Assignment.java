package com.muf.modules.workflow.assignment;

import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "assignments")
@IdClass(AssignmentId.class)
public class Assignment extends AssignmentEntity{
}
