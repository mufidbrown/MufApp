package com.muf.modules.module.entity;


import com.muf.base.entity.BaseEntity;
import com.muf.modules.module.entity.domain.ModuleGroup;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.Where;
import com.muf.modules.module.entity.domain.Module;
import java.io.Serializable;

@SuppressWarnings("serial")
@MappedSuperclass
public class ModuleDetailEntity extends BaseEntity implements Serializable {

    @ManyToOne
    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
    @JoinColumn(name="module_id", referencedColumnName="id", nullable = false)
    private Module module;

    @ManyToOne
    @Where(clause = "is_deleted = " + BaseEntity.ENTITY_FLAG_NOT_DELETED)
    @JoinColumn(name="module_group_id", referencedColumnName="id", nullable = false)
    private ModuleGroup moduleGroup;

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public ModuleGroup getModuleGroup() {
        return moduleGroup;
    }

    public void setModuleGroup(ModuleGroup moduleGroup) {
        this.moduleGroup = moduleGroup;
    }

}

