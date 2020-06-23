package com.pch;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

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

        String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        Map<String, String> map = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));

        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);
            if (annotation != null) {
                String value = annotation.value().isEmpty() ? map.get(field.getName()) : map.get(annotation.value());
                field.setAccessible(true);
                field.set(t, value);
            }
        }

        return t;
    }
}