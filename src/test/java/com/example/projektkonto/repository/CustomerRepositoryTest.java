package com.example.projektkonto.repository;

import com.example.projektkonto.model.CustomerData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {
    @Test
    void randompersonalIdTest(){
        var x = new CustomerRepository();
        String name = x.getRandomPersonalId("Tomek", "Atomek");
        assertEquals("atotom", name.substring(0,6));
    }

    @Test
    void saveTest(){
        var x = new CustomerRepository();
        CustomerData data = x.save("Tomek", "Atomek");
        CustomerData datatest = new CustomerData("atotom", "Tomek", "Atomek");
        assertEquals(datatest.getPersonalId(), data.getPersonalId().substring(0,6));
        assertEquals(datatest.getFirstName(), data.getFirstName());
        assertEquals(datatest.getLastName(), data.getLastName());
    }

}