package ru.otus.java;

public class RandomHelper {
    public static Client generateClient(){
        int num = 0;
        num ++;
        return new Client("Prutkov K." + num, (int)Math.random() * 100);
    }

    public static Account generateAccount() {
        return new Account((long)Math.random() * 1000000000000L,  (int)Math.random() * 1000);
    }
}
