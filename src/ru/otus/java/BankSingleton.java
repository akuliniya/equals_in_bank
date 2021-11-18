package ru.otus.java;

import java.util.*;

public class BankSingleton implements BankRepository {
    private static BankSingleton BANK;
    private BankSingleton(){}

    public static synchronized BankSingleton getBankInstance(){
        if(BANK == null){
            BANK = new BankSingleton();
        }
        return BANK;
    }

    private HashMap<Account, Client> accountsList = new HashMap<>();
    private HashSet<Client> clientsList;

    public HashMap<Account, Client> getAccountsList(){
        return accountsList;
    }

    public HashSet<Client> getClientsList() {
        return clientsList;
    }

    @Override
    public void addAccount(Account account, Client client) {
//        if (accountsList.equals(null)) {
//            accountsList = new HashMap<Account, Client>();
//        }
        accountsList.put(account, client);
    }

    @Override
    public Iterable<Account> getAccounts(Client client) {
        ArrayList<Account> clientAccountsList = new ArrayList<Account>();
        Set<Map.Entry<Account, Client>> entries = accountsList.entrySet();
        for (Map.Entry<Account, Client> pair : entries){
            if (client.equals(pair.getValue())){
                clientAccountsList.add(pair.getKey());
            }
        }
        return clientAccountsList;
    }

    @Override
    public void deleteAccount(Account account) {
        accountsList.remove(account);
    }

    @Override
    public void addClient(Client client) {
        if (clientsList.equals(null)) {
            clientsList = new HashSet<Client>();
        }
        clientsList.add(client);
    }

    @Override
    public Client findClient(Account account) {
        return accountsList.get(account);
    }

    @Override
    public void deleteClient(Client client) {
        clientsList.remove(client);
        TreeSet<Map.Entry<Account, Client>> entrySet = (TreeSet<Map.Entry<Account, Client>>) accountsList.entrySet();
        for (Map.Entry<Account, Client> pair : entrySet){
            if (client.equals(pair.getValue())){
                accountsList.remove(pair.getKey());
            }
        }
    }
}
