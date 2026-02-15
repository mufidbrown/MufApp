package com.muf.modules.workflow.opportunity;

import com.muf.base.entity.BaseEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class OpportunityEntity extends BaseEntity implements Serializable {
}
