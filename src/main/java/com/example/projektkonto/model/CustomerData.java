package com.example.projektkonto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CustomerData {
    private String personalId;
    private String firstName;
    private String lastName;

    public CustomerData(CustomerData data){
        this.personalId = data.getPersonalId();
        this.firstName = data.getFirstName();
        this.lastName = data.getLastName();
    }
    public CustomerData(){

    }
}
