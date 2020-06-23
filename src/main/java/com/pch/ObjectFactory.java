package com.pch;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ObjectFactory {

    private Config config;
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private ApplicationContext context;

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        Map<Class, Class> map = new HashMap<>();
        map.put(Policeman.class, PolicemanImpl.class);
        config = new JavaConfig("com.pch", map);
        this.context = context;
        for (Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        T t = implClass.getDeclaredConstructor().newInstance();

        //
        configurators.forEach(configurator -> configurator.configure(t, context));

        return t;
    }
}