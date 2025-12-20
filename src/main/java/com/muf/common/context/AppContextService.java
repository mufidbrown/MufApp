package com.muf.common.context;

import com.muf.common.constant.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class AppContextService {

    private final CacheManager cacheManager;
    private static HashMap<String, String> parameterMap = new HashMap<String, String>();

    public AppContextService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public String getParameter(String key) {
        return getCache("app-parameter").get(key, String.class);
    }

    public List<String> getRoleServices(Integer roleId) {
        return getCache("role-services").get(roleId, List.class);
    }

    public void putRoleServices(Integer roleId, List<String> services){
        getCache("role-services").put(roleId, services);
    }

    private Cache getCache(String name) {
        Cache cache = cacheManager.getCache(name);
        if (cache == null) throw  new IllegalStateException("Cache not found: " + name);
        return cache;
    }

    public static String getParameterStringByCode(Enum<?> pEnum) {

        String value = parameterMap.get(pEnum.toString());
        return value;
    }

    public static Boolean getParameterBooleanByCode(Enum<?> pEnum) {
        String val = getParameterStringByCode(pEnum);
        return val == null ? false : (val.equalsIgnoreCase("TRUE") ? true : false);
    }

    public static Double getParameterDoubleByCode(Enum<?> pEnum) {
        String val = getParameterStringByCode(pEnum);
        return val == null ? null : Double.parseDouble(val);
    }

    public static Date getParameterTimeByCode(Enum<?> pEnum) {
        Date finalDate = new Date();
        String val = getParameterStringByCode(pEnum);
        try {
            finalDate = new SimpleDateFormat(AppContextService.getParameterStringByCode(AppConstant.ParameterEnum.TIME_FORMAT_SHORT)).parse(val);
        } catch (ParseException e) {
            return null;
        }
        return finalDate;
    }


}

