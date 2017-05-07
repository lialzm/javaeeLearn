package com.catfish.config;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * Created by A on 2017/4/24.
 */
public class CustomKeyGenerator implements KeyGenerator {
    public Object generate(Object o, Method method, Object... objects) {
        StringBuilder sb = new StringBuilder();
        sb.append(o.getClass().getName());
        sb.append("."+method.getName());
        for (Object obj : objects) {
            sb.append(obj.toString());
        }
        return sb.toString();
    }
}
