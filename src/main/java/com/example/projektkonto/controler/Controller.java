package com.example.projektkonto.controler;

import com.example.projektkonto.model.AccountData;
import com.example.projektkonto.model.CustomerData;
import com.example.projektkonto.response.Error;
import com.example.projektkonto.response.Success;
import com.example.projektkonto.service.AccountService;
import com.example.projektkonto.service.CustomerService;
import com.example.projektkonto.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/aliorbank")
public class Controller {
    private AccountService accountService;
    private CustomerService customerService;

    @Autowired
    public Controller(AccountService accountService, CustomerService customerService){
        this.accountService = accountService;
        this.customerService = customerService;
    }


    @PostMapping("/personalData")
    public ResponseEntity<?> personalData(@RequestBody CustomerData data){
        ResponseEntity<?> responseEntity = customerService.createAndReturnPersonalId(data);
        Success successResponse = (Success) responseEntity.getBody();
        CustomerData customerData = (CustomerData) successResponse.getObject();
        EntityModel<Success> resource = EntityModel.of(successResponse);;
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).createAccount(new AccountData(),customerData.getPersonalId())).withRel("createAccount"));
        return new ResponseEntity<> (resource, HttpStatus.OK) ;

    }

    @PostMapping("/createAccount/{personalId}")
    public ResponseEntity<?> createAccount(@RequestBody AccountData konto, @PathVariable String personalId){
        ResponseEntity<?> responseEntity = accountService.createAndReturnAccountNumber(personalId, konto.getProductType(), konto.getAccountName());
        Success successResponse = (Success) responseEntity.getBody();
        AccountData accountData = new AccountData((AccountData) successResponse.getObject());
        EntityModel<AccountData> resource = EntityModel.of(accountData);
        List<String> allowedAction = allowedActions();
        allowedAction.stream().forEach(action ->{
            if(action.equalsIgnoreCase("FETCHDETAILS"))
            {
                resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).find(accountData.getAccountNumber())).withRel("fetchDetails"));
            }
            if(action.equalsIgnoreCase("MODIFY"))
            {
                resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).modifyAccountName(new AccountData())).withRel("modify"));
            }
            if(action.equalsIgnoreCase("CLOSE"))
            {
                resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).delete(accountData.getAccountNumber())).withRel("closeaccount"));
            }
        });


        return new ResponseEntity<>(resource,HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> find(@PathVariable String accountNumber){
        ResponseEntity<?> responseEntity = accountService.fetchDetails(accountNumber);
        if(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST)){
            ErrorService x = new ErrorService();
            Error error =  x.accountDataNotfound("404 Not found", null,404);
            return new ResponseEntity<>(error, HttpStatus.OK);
        }
        else{
            Success successResponse =  new Success((Success) responseEntity.getBody());
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        }

    }

    @PutMapping("/modify")
    public ResponseEntity<?> modifyAccountName(@RequestBody AccountData konto){
        ResponseEntity<?> responseEntity = accountService.modifyAccountName(konto.getAccountNumber(), konto.getAccountName());
        return new ResponseEntity<>(responseEntity, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<?> delete(@PathVariable String accountNumber){
        ResponseEntity<?> responseEntity = accountService.close(accountNumber);
        if(responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST)){
            ErrorService x = new ErrorService();
            Error error =  x.accountDataNotfound("404 Not found", null,404);
            return new ResponseEntity<>(error, HttpStatus.OK);
        }
        else{
            Success successResponse =  new Success(responseEntity.getBody().toString());
            return new ResponseEntity<>(successResponse.getMessage(), HttpStatus.OK);
        }

    }


    protected List<String> allowedActions(){

        List<String> list = new ArrayList<>();
        list.add("FETCHDETAILS");
        list.add("MODIFY");
        list.add("CLOSE");

        return list;
    }


}
