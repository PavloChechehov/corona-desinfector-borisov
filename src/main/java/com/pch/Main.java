package com.pch;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Class, Class> map = new HashMap<>();
        map.put(Policeman.class, PolicemanImpl.class);
        ApplicationContext context = Application.run("com.pch", map);
        CoronaDesinfector coronaDesinfector = context.getObject(CoronaDesinfector.class);
        coronaDesinfector.start(new Room());
    }
}
