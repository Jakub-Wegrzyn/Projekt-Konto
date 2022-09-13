package com.example.projektkonto.service;

import com.example.projektkonto.model.AccountData;
import com.example.projektkonto.repository.AccountRepository;
import com.example.projektkonto.response.Error;
import com.example.projektkonto.response.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements com.example.projektkonto.GlobalBank.AccountService {

    private AccountRepository repository;
    @Autowired
    public AccountService(AccountRepository repository){
        this.repository = repository;
    }


    @Override
    public ResponseEntity<?> createAndReturnAccountNumber(String personalId, String productType, String accountName) { //create
        if(personalId!= null && productType!=null && accountName!=null){
            AccountData accountData = repository.save(personalId,productType,accountName);
            Success success = new Success("Success", accountData, "200");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        else{
            ErrorService errorService = new ErrorService();
            Error error = errorService.accountDataNotfound("Not found", null, 404);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<?> modifyAccountName(String accountNumber, String accountName) { //update
        AccountData accountData = repository.modfiy(accountNumber, accountName);
        return new ResponseEntity<>(accountData, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> fetchDetails(String accountNumber) {
        AccountData accountData = repository.getAccount(accountNumber);
        if(accountData!=null){
            Success success = new Success("Your data below", accountData, "200");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        else{
            ErrorService errorService = new ErrorService();
            Error error = errorService.accountDataNotfound("Not found", null, 404);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> close(String accountNumber) { //delete
        String message = repository.delete(accountNumber);
        if(message.equals("Succesful delete")){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
