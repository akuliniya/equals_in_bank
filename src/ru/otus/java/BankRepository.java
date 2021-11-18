package ru.otus.java;

import java.util.Collection;

public interface BankRepository {
    void addAccount(Account ac, Client c);
    Iterable<Account> getAccounts(Client c);
    void deleteAccount(Account a);

    void addClient(Client c);
    Client findClient(Account a);
    void deleteClient(Client c);


}
