package com.muf.core.engine;

import com.muf.base.service.BaseEngineService;
import com.muf.common.constant.AppConstant;
import com.muf.modules.translation.TranslationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class TranslatorEngineService extends BaseEngineService {

    private static final Logger logger = LoggerFactory.getLogger(TranslatorEngineService.class);

    private final TranslationRepository translationRepository;

    public TranslatorEngineService(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }

    @Override
    protected String getModuleCode() {
        return AppConstant.MCODE_TRANSLATOR_ENGINE;
    }

    /*Translate Key berdasarkan language*/
    public String translate(String langCode, String key) {

        if (key == null || key.isBlank()) {
            return key;
        }

        return translationRepository
                .findValueByKeyAndLang(key, langCode)
                .orElseGet(() -> {
                    logger.warn("Translation not found: key={}, lang={}", key, langCode);
                    return key;
                });

    }
}
