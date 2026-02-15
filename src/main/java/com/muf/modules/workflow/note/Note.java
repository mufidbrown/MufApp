package com.muf.modules.workflow.note;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "notes")
public class Note extends NoteEntity{
}
