package com.pch;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;

class ObjectFactory {

    private static ObjectFactory instance = new ObjectFactory();
    private Config config = new JavaConfig("com.pch");

    private ObjectFactory() {
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