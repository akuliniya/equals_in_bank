package ru.otus.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BankProcessing {
    public static void main(String[] args) {
        BankSingleton bank = BankSingleton.getBankInstance();
        for (int i = 0; i < 100; i++) {
            bank.addAccount(RandomHelper.generateAccount(), RandomHelper.generateClient());
        }

        Client clientForTest = RandomHelper.generateClient();
        Account accountForClient1 = RandomHelper.generateAccount();
        Account accountForClient2 = RandomHelper.generateAccount();
        Account accountForClient3 = RandomHelper.generateAccount();
        Account accountForClient4 = RandomHelper.generateAccount();

        bank.addAccount(accountForClient1, clientForTest);
        bank.addAccount(accountForClient2, clientForTest);
        bank.addAccount(accountForClient3, clientForTest);
        bank.addAccount(accountForClient4, clientForTest);

        HashMap<Account, Client> accountCsList = bank.getAccountsList();
        printBankAccountsList(accountCsList);

        Iterable<Account> testClientAccounts = bank.getAccounts(clientForTest);
        for ( Account a : testClientAccounts) {
            System.out.println(Account.toString(a));
        }

        System.out.println(Client.toString(bank.findClient(accountForClient1)));
        System.out.println(Client.toString(bank.findClient(accountForClient2)));
        System.out.println(Client.toString(bank.findClient(accountForClient3)));
        System.out.println(Client.toString(bank.findClient(accountForClient4)));
    }

    public static void printBankAccountsList(Map<Account, Client> accounts) {
        Set<Map.Entry<Account, Client>> entries = accounts.entrySet();
        for(Map.Entry<Account, Client> pair: entries) {
            String key = Account.toString(pair.getKey());
            String value = Client.toString(pair.getValue());
            System.out.println(key + " --> " + value);
        }
    }
}
