package com.example.projektkonto.repository;

import com.example.projektkonto.model.AccountData;
import com.example.projektkonto.model.CustomerData;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

@Repository
public class CustomerRepository {
    public HashMap<String, CustomerData> mapperson = new HashMap<String, CustomerData>();
    public Set<Integer> set = new LinkedHashSet<Integer>();
    public Random rand = new Random();

    public CustomerData save(String firstName, String lastName) {
        CustomerData person = new CustomerData();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        String randomPersonalId = getRandomPersonalId(firstName,lastName);
        while(set.contains(randomPersonalId)){
            randomPersonalId = getRandomPersonalId(firstName,lastName);
        }
        person.setPersonalId(randomPersonalId);
        mapperson.put(randomPersonalId, person);
        return person;
    }

    public String getRandomPersonalId(String firstName, String lastName){
        String firstName3 = firstName.substring(0,3).toLowerCase();
        String lastName3 = lastName.substring(0,3).toLowerCase();
        int random4number = rand.nextInt(8999)+1000;
        String last4Number = String.valueOf(random4number);
        String randomPersonalId = lastName3 + firstName3 + last4Number;
        return randomPersonalId;
    }
}
