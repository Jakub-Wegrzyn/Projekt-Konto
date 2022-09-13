package com.example.projektkonto.service;

import com.example.projektkonto.model.CustomerData;
import com.example.projektkonto.repository.CustomerRepository;
import com.example.projektkonto.response.Error;
import com.example.projektkonto.response.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements com.example.projektkonto.GlobalBank.CustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> createAndReturnPersonalId(CustomerData data) {

        if (data != null) {
            CustomerData customerData = repository.save(data.getFirstName(), data.getLastName());
            Success success = new Success("Success", customerData, "200");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        else {
            ErrorService errorService = new ErrorService();
            Error error = errorService.accountDataNotfound("Not found", null, 400);
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

    }


    @Override
    public boolean hasCustomerFile(String personalId, String firstName, String lastName) {
        return false;
    }
}
