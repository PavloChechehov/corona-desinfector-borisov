package com.pch;

public class ConsoleAnnouncer implements Announcer {
    public void annonce(String message) {
        System.out.println(message);
    }
}
