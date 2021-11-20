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
        accountsList.put(account, client);
        fillHelpListForSearchClientAccounts(client, account);
    }

    private void fillHelpListForSearchClientAccounts(Client client, Account account) {
        if (helpListForSearchClientAccounts == null) {
            helpListForSearchClientAccounts = new HashMap<>();
        }

       if (helpListForSearchClientAccounts.containsKey(client)) {
           helpListForSearchClientAccounts.get(client).add(account);
       }else{
           HashSet<Account> clientAccounts = new HashSet<>();
           clientAccounts.add(account);
           helpListForSearchClientAccounts.put(client, clientAccounts);
        }
    }

    @Override
    public void deleteAccount(Account account) {
        accountsList.remove(account);
    }

    @Override
    public Client findClient(Account account) {
        return accountsList.get(account);
    }

    @Override
    public Iterable<Account> getAccounts(Client client) {
        return helpListForSearchClientAccounts.get(client);
    }

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
