package com.pch;

import java.util.Map;

public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> ifc2implClass) {
        JavaConfig javaConfig = new JavaConfig(packageToScan, ifc2implClass);
        ApplicationContext applicationContext = new ApplicationContext(javaConfig);
        ObjectFactory objectFactory = new ObjectFactory(applicationContext);
        applicationContext.setFactory(objectFactory);
        //todo init all singletons which are not lazy
        return applicationContext;
    }
}
