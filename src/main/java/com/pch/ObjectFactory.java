package com.pch;

import lombok.SneakyThrows;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class ObjectFactory {

    private static ObjectFactory instance = new ObjectFactory();
    private Config config;

    private ObjectFactory() {
        Map<Class, Class> map = new HashMap<>();
        map.put(Policeman.class, PolicemanImpl.class);
        config = new JavaConfig("com.pch", map);
    }

    public static ObjectFactory getInstance() {
        return instance;
    }


    @SneakyThrows
    public <T> T createObject(Class<T> type) {

        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T t = implClass.getDeclaredConstructor().newInstance();

        //

        return t;
    }
}