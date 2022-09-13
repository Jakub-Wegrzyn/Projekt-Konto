package com.example.projektkonto.repository;

import com.example.projektkonto.GlobalBank.CustomerService;
import com.example.projektkonto.model.CustomerData;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Random;


@Repository
public class CustomerRepository implements CustomerService {
    public HashMap<String, CustomerData> mapperson = new HashMap<String, CustomerData>();
    public Random rand = new Random();

    public CustomerData save(String firstName, String lastName) {
        CustomerData person = new CustomerData();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        String randomPersonalId = getRandomPersonalId(firstName,lastName);
        while(mapperson.containsKey(randomPersonalId)){
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

    @Override
    public boolean hasCustomerFile(String personalId, String firstName, String lastName) {
        if(mapperson.containsKey(personalId)){
            return false;
        }
        else{
            return true;
        }

    }
}
