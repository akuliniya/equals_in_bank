package ru.otus.java;

import java.util.*;

public class BankSingleton implements BankRepository {
    private HashMap<Account, Client> accountsList;
    private HashMap<Client, HashSet<Account>> helpListForSearchClientAccounts;

    private static BankSingleton BANK;
    private BankSingleton(){}
    public static synchronized BankSingleton getBankInstance(){
        if(BANK == null){
            BANK = new BankSingleton();
        }
        return BANK;
    }

    public HashMap<Account, Client> getAccountsList(){
        return accountsList;
    }

    @Override
    public void addAccount(Account account, Client client) {
        if (accountsList == null) {
            accountsList = new HashMap<>();
        }
        if (!accountsList.containsKey(account)){
            accountsList.put(account, client);
            fillHelpListForSearchClientAccounts(client, account);
        }else {
            System.out.println("Счет уже добавлен ранее.");
        }

    }

    private void fillHelpListForSearchClientAccounts(Client client, Account account) {
        if (helpListForSearchClientAccounts == null) {
            helpListForSearchClientAccounts = new HashMap<>();
        }
       if (!helpListForSearchClientAccounts.containsKey(client)) {
           HashSet<Account> clientAccounts = new HashSet<>();
           clientAccounts.add(account);
           helpListForSearchClientAccounts.put(client, clientAccounts);
       }else{
           if (!helpListForSearchClientAccounts.get(client).contains(account)){
               helpListForSearchClientAccounts.get(client).add(account);
           }else {
               System.out.println("Счет уже добавлен ранее.");
           }
        }
    }

    @Override
    public void deleteAccount(Account account) {
        Client client = findClient(account);
        accountsList.remove(account);
        helpListForSearchClientAccounts.get(client).remove(account);
    }

    // Что делать с кейсом, когда возвращает nul?
    @Override
    public Client findClient(Account account) {
        Client client = accountsList.get(account);
        if (client == null) {
            System.out.println("Такой клиент не найден.");
        }
        return client;
    }

    // Попробовала с Optional.empty(), не поняла удобства
    public Optional<Client> findClientByAccount(Account account) {
        return accountsList.get(account) == null ? Optional.empty() : Optional.ofNullable(accountsList.get(account));
//        try {
//            return findClient(account);
//        }catch (NullPointerException e) {
//            return Optional.empty();
//        }
    }

    // Что делать с кейсом, когда возвращает nul?
    @Override
    public Iterable<Account> getAccounts(Client client) {
        HashSet<Account> clientAccounts;
        if (helpListForSearchClientAccounts.containsKey(client)) {
            clientAccounts = helpListForSearchClientAccounts.get(client);
        }else {
            clientAccounts = null;
            System.out.println("Счетов для клиента не найдено.");
        }
        return clientAccounts;
    }

//    Вариант реализации поиска клиента с помощью Map.Entry. Должен быть более медленным
//    public Iterable<Account> getAccounts(Client client) {
//        ArrayList<Account> clientAccountsList = new ArrayList<Account>();
//        Set<Map.Entry<Account, Client>> entries = accountsList.entrySet();
//        for (Map.Entry<Account, Client> pair : entries){
//            if (client.equals(pair.getValue())){
//                clientAccountsList.add(pair.getKey());
//            }
//        }
//        return clientAccountsList;
//    }
}
