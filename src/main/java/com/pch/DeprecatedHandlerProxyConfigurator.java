package com.pch;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithProxyIfNeeded(Object t, Class implClass) {

        if (!implClass.isAnnotationPresent(Deprecated.class)) {
            return t;
        }

        return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("******* Що ти робиш урод? Цей клас є @Deprecated");
                return method.invoke(t, args);
            }
        });
    }
}
