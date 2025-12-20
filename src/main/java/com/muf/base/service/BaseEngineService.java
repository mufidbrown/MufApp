package com.muf.base.service;

import org.springframework.stereotype.Service;

@Service
public abstract class BaseEngineService extends BaseService {

    protected abstract String getModuleCode();

}
