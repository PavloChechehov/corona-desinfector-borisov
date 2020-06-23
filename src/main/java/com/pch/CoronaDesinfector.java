package com.pch;

public class CoronaDesinfector {

    private static Announcer announcer = ObjectFactory.getInstance().createObject(Announcer.class);
    private static Policeman policeman = ObjectFactory.getInstance().createObject(Policeman.class);

    public void start(Room room) {
        //todo оголошення про провітрювання кімнати
        announcer.annonce("Необхідно негайно продезинфікувати кімнату. Всі геть!");
        //todo  поліція розганяє всіх
        policeman.getOutFrom();
        //todo в кімнату можна вертатися
        announcer.annonce("Всім можна заходити в кімнату, якщо не боїтесь за своє здоровя)");

        disinfect(room);

    }

    private static void disinfect(Room room) {
        System.out.println("room " + room + " вже чиста!!!");
    }
}
