package com.pch;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ObjectFactory {

    private static ObjectFactory instance = new ObjectFactory();
    private Config config;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private Map<Class, Class> map = new HashMap<>();

    @SneakyThrows
    private ObjectFactory() {
        map.put(Policeman.class, PolicemanImpl.class);
        config = new JavaConfig("com.pch", map);
        for (Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
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
        configurators.forEach(configurator -> configurator.configure(t));
        
        return t;
    }
}