package ru.otus.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BankProcessing {
    public static void main(String[] args) {
        BankSingleton bank = BankSingleton.getBankInstance();
        fillInWithRandomData(bank);

        //Клиенты и счета для теста
        Client masha = new Client("Masha Granatova", 25);
        Client dima = new Client("Dima Konyashkin", 27);

        Account account1 = new Account(111111111111L, 21000);
        Account account2 = new Account(2222222222222L, 22000);
        Account account3 = new Account(3333333333333L, 23000);
        Account account4 = new Account(5555555555555L, 25000);

        //Добавляем их в список счетов
        bank.addAccount(account1, masha);
        bank.addAccount(account2, dima);
        bank.addAccount(account3, masha);
        bank.addAccount(account4, dima);

        //Получить и вывести на печать заполненный список счетов и клиентов
        HashMap<Account, Client> accountCsList = bank.getAccountsList();
        printBankAccountsList(accountCsList);

        //Исчем счета по клиенту и вводим на печать
        System.out.println("Счета найденные по клиенту 1: " + Client.toString(masha));
        Iterable<Account> testClientAccounts1 = bank.getAccounts(masha);
        for (Account a : testClientAccounts1) {
            System.out.println(Account.toString(a));
        }

        System.out.println("Счета найденные по клиенту 2: " + Client.toString(dima));
        Iterable<Account> testClientAccounts2 = bank.getAccounts(dima);
        for (Account a : testClientAccounts2) {
            System.out.println(Account.toString(a));
        }

        //Ищем клиента по счету и выводим не печать
        System.out.println("Клиенты, найденные по номеру счета");
        System.out.println(Account.toString(account1) + " " + Client.toString(bank.findClient(account1)));
        System.out.println(Account.toString(account2) + " " + Client.toString(bank.findClient(account2)));
        System.out.println(Account.toString(account3) + " " + Client.toString(bank.findClient(account3)));
        System.out.println(Account.toString(account4) + " " + Client.toString(bank.findClient(account4)));

    }

    public static void printBankAccountsList(Map<Account, Client> accounts) {
        Set<Map.Entry<Account, Client>> entries = accounts.entrySet();
        for(Map.Entry<Account, Client> pair: entries) {
            String key = Account.toString(pair.getKey());
            String value = Client.toString(pair.getValue());
            System.out.println(key + " --> " + value);
        }
        System.out.println();
    }

    private static void fillInWithRandomData(BankSingleton bank) {
        for (int i = 0; i < 100; i++) {
            Client client = RandomHelper.generateClient();
            Account account = RandomHelper.generateAccount();
            bank.addAccount(account, client);
        }
    }
}
