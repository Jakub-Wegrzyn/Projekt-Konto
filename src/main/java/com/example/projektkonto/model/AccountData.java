package com.example.projektkonto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountData {
    private String personalId;
    private String accountNumber;
    private String productType;
    private String accountName;

    public AccountData(AccountData data){
        this.accountNumber = data.getAccountNumber();
    }
}


