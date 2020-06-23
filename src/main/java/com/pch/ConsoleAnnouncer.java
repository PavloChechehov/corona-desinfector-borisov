package com.pch;

public class ConsoleAnnouncer implements Announcer {

    @InjectByType
    private Recommendator recommendator;

    public void annonce(String message) {
        System.out.println(message);
        recommendator.recommend();
    }
}
