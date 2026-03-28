package com.muf.modules.analytics.salesperformancereport;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
@Table(name = "salesperformances")
public class SalesPerformance extends SalesPerformanceEntity {
}
