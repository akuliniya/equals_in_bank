package ru.otus.java;

import java.util.HashSet;

public class Client {
    private static long idCounter = 0L;
    private final long clientId;
    private String name;
    private int age;

    public Client(String name, int age) {
        idCounter++;
        this.clientId = idCounter;
        this.name = name;
        this.age = age;
    }

    public long getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

//    public boolean isAgeAppropriate(int ageFrom, int ageTo){
//        return (age >= ageFrom && age < ageTo);
//    }
//
//    public boolean isAgeAppropriate(int ageFrom){
//        return (age >= ageFrom);
//    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (getClass() != other.getClass()) return false;

        Client otherClient = (Client) other;
        return this.name.equals(otherClient.name);
    }


    public static String toString(Client client) {
        return Long.toString(client.getClientId()) + " " + client.getName().toString();
    }
}
