package com.muf.base.service;

import com.muf.core.engine.TranslatorEngineService;
//import com.muf.core.session.CurrentUserSession;
import io.micrometer.common.util.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BaseService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    protected CurrentUserSession currentUserSession;

//    @Autowired
//    protected TranslatorEngineService translatorEngineService;

    public boolean isNotEmpty(String text) {
        return StringUtils.isNotEmpty(text);
    }

    public boolean isEmpty(String text) {
        return StringUtils.isEmpty(text);
    }

    public boolean isNotEmpty(List<?> list) {
        return CollectionUtils.isNotEmpty(list);
    }

    public boolean isEmpty(List<?> list) {
        return CollectionUtils.isEmpty(list);
    }

//    public CurrentUserSession getCurrentUserSession() {
//        return currentUserSession;
//    }
//
//    public void setCurrentUserSession(CurrentUserSession currentUserSession) {
//        this.currentUserSession = currentUserSession;
//    }

}