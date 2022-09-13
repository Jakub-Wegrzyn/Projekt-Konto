package com.example.projektkonto.repository;

import com.example.projektkonto.model.AccountData;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Random;

@Repository
public class AccountRepository {

    public HashMap<String, AccountData> accountmap = new HashMap<String, AccountData>();

    public AccountData save(String personalId, String productType, String accountName) {
        AccountData konto = new AccountData();
        konto.setPersonalId(personalId);
        konto.setProductType(productType);
        konto.setAccountName(accountName);
        String accountnumber = randomAccountNumber();
        while(accountmap.containsKey(accountnumber)){
            accountnumber = randomAccountNumber();
        }
        konto.setAccountNumber(accountnumber);
        accountmap.put(accountnumber, konto);
        return konto;
    }
    public AccountData getAccount(String accountNumber){
        if(accountmap.containsKey(accountNumber)){
            return accountmap.get(accountNumber);
        }
        else
            return null;

    }

    public AccountData modfiy(String accountNumber, String accountName){
        AccountData konto = new AccountData();
        if(accountmap.containsKey(accountNumber)){
            accountmap.get(accountNumber).setAccountName(accountName);
            return accountmap.get(accountNumber);
        }
        else if(!accountmap.containsKey(accountNumber)){
            return null;
        }
        konto = accountmap.get(accountNumber);

        return konto;
    }
    public String delete(String accountNumber){
        String message;
        if(accountmap.containsKey(accountNumber)){
            accountmap.remove(accountNumber);
            message = "Succesful delete";
        }
        else{
            message = "Data not found";
        }
        return message;
    }

    public String randomAccountNumber(){
        Random rand = new Random();
        String randaccountnumber = null;
        StringBuilder upperalphabet = new StringBuilder();
        StringBuilder loweralphabet = new StringBuilder();

        char c;
        for(c = 'a'; c <= 'z'; c++){
            loweralphabet.append(c);
        }

        char x;
        for(x = 'A'; x <= 'Z'; x++){
            upperalphabet.append(x);
        }

        StringBuilder alphabet = new StringBuilder();
        alphabet.append(loweralphabet).append(upperalphabet);

        int random4digits = rand.nextInt(8999)+1000;
        int random3digits = rand.nextInt(899)+100;

        char[] random8letter = new char[8];
        for(int i =0; i<8; i++){
            random8letter[i]=alphabet.charAt(rand.nextInt(alphabet.length()));
        }
        String r4 = String.valueOf(random4digits);
        String r3 = String.valueOf(random3digits);
        String r8 = new String(random8letter);
        randaccountnumber = r3 + r8 + r4;

        return String.valueOf(randaccountnumber);
    }
}


