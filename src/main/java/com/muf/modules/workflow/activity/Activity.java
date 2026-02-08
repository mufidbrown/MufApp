package com.muf.modules.workflow.activity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "activitys")
public class Activity extends ActivityEntity{
}
