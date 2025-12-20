package com.muf.modules.module.service;

import com.muf.modules.module.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ModuleService{

    @Autowired
    private ModuleRepository moduleRepository;


//    @Override
//    public ModuleRepository getRepository() {
//        return moduleRepository;
//    }

//    @Transactional(readOnly = true)
//    public Module getByDefault(String code) {
//        return moduleRepository.getByDefault(code);
//    }
/*

    @Transactional(readOnly = true)
    public List<Module> getListByDashboard() {
        return moduleRepository.getListByDashboard();
    }

    @Transactional(readOnly = true)
    public List<Module> getListNotByWorkflowIdAndModuleGroupCode(Integer workflowId, String moduleGroupCode) {
        return ModuleRepository.getListNotByWorkflowIdAndModuleGroupCode(workflowId, moduleGroupCode);
    }

    @Transactional(readOnly = true)
    public List<Module> getListByWorkflowId(Integer workflowId) {
        return moduleRepository.getListByWorkflowId(workflowId);
    }

    @Transactional(readOnly = true)
    public List<Module> getStarterListByModuleGroupCode(String moduleGroupCode) {
        return moduleRepository.getStarterListByModuleGroupCode(moduleGroupCode);
    }

    @Transactional(readOnly = true)
    public List<Module> getStageListByModuleGroupCode(String moduleGroupCode) {
        return moduleRepository.getStageListByModuleGroupCode(moduleGroupCode);
    }
*/

/*    @Transactional(readOnly = true)
    public List<Module> convertIntoModuleDependencyString(List<Module> moduleList) {
        for(Module module : moduleList) {
            String input = "";
            String output = "";
            List<ModuleDependency> moduleDependencyList = moduleDependencyDAO.getByModuleId(module.getId());
            for(ModuleDependency md : moduleDependencyList) {
                if(md.getType() == AppConstant.MODULE_DEPENDENCY_TYPE_INPUT) {
                    input += md.getDependency().getName() + ", ";
                }else {
                    output += md.getDependency().getName() + ", ";
                }
            }
            input = input.replaceAll(", $", "");
            output = output.replaceAll(", $", "");
            module.setInputDependency(input);
            module.setOutputDependency(output);
        }
        return moduleList;
    }*/
/*

    @Transactional(readOnly = true)
    public List<Module> getStarterList() {
        return moduleRepository.getStarterList();
    }

    @Transactional(readOnly = true)
    public List<Module> getStageListFromWorkflowModelPrcsByModuleGroupCode(String moduleGroupcode) {
        return moduleRepository.getStageListFromWorkflowModelPrcsByModuleGroupCode(moduleGroupcode);
    }

    @Transactional(readOnly = true)
    public List<Module> getStarterListFromWorkflowPrcsByModuleGroupCode(String moduleGroupcode) {
        return moduleRepository.getStarterListFromWorkflowPrcsByModuleGroupCode(moduleGroupcode);
    }
*/

}
