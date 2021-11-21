package ru.otus.java;

import java.util.*;

public class Account {
    private final long accountNumber;
    private long accountBalance;

    public Account(long accountNumber, long accountBalance) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public Account(long accountNumber) {
        this(accountNumber, 0);
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(long accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public int hashCode() {
        return Long.toString(accountNumber).hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (getClass() != other.getClass()) return false;

        Account otherAccount = (Account) other;
        return Objects.equals(this.accountNumber, otherAccount.accountNumber);
    }

    // Какой вариант toString() правильнее: в Account или в Client?
    public static String toString(Account account) throws NullPointerException {
        return account.getAccountNumber() + " " + account.getAccountBalance();
    }
}
