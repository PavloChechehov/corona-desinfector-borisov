package com.pch;

public class PolicemanImpl implements Policeman {

    @InjectByType
    private Recommendator recommendator;

    public void getOutFrom() {
        System.out.println("Геть з кімнати а то буду стріляти");
    }
}
