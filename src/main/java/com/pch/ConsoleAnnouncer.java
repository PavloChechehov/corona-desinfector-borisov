package com.pch;

public class ConsoleAnnouncer implements Announcer {

    private Recommendator recommendator = ObjectFactory.getInstance().createObject(Recommendator.class);
    public void annonce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
