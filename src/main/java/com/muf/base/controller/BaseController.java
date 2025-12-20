package com.muf.base.controller;



import com.muf.core.session.CurrentUserSession;
import com.muf.modules.module.service.ModuleService;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping
public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrentUserSession currentUserSession;

    @Autowired
    private ModuleService moduleService;

//    private OrganizationBeanService organizationBeanService;

    protected abstract String getModuleCode();

    protected boolean isNotEmpty(String text) {
        return StringUtils.isNotEmpty(text);
    }

    protected boolean isEmpty(String text) {
        return StringUtils.isEmpty(text);
    }

    public boolean isNotEmpty(List<?> list) {
        return !CollectionUtils.isEmpty(list);
    }

    public boolean isEmpty(List<?> list) {
        return CollectionUtils.isEmpty(list);
    }

    public CurrentUserSession getCurrentUserSession() {
        return currentUserSession;
    }

    public void setCurrentUserSession(CurrentUserSession currentUserSession) {
        this.currentUserSession = currentUserSession;
    }
}
