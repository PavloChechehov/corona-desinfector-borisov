package com.pch;

import javax.annotation.PostConstruct;

public class PolicemanImpl implements Policeman {

    @InjectByType
    private Recommendator recommendator;

    @PostConstruct
    public void init() {
        System.out.println(recommendator.getClass());
    }

    public void getOutFrom() {
        System.out.println("Геть з кімнати а то буду стріляти");
    }
}
