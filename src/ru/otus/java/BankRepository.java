package ru.otus.java;

import java.util.Collection;

public interface BankRepository {
    void addAccount(Account ac, Client c);
    void deleteAccount(Account a);
    Iterable<Account> getAccounts(Client c);
    Client findClient(Account a);
}
