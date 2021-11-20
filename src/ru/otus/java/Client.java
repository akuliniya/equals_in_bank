package ru.otus.java;

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
        return client.getClientId() + " " + client.getName();
    }
}
