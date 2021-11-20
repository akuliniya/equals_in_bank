package ru.otus.java;

public class RandomHelper {
    private static int clientCounter = 0;
    public static Client generateClient(){
        clientCounter ++;
        return new Client("Prutkov K." + clientCounter, (int)(Math.random() * 100));
    }

    public static Account generateAccount() {
        return new Account((long)(Math.random() * 1000000000000L),  (int)(Math.random() * 1000));
    }
}
