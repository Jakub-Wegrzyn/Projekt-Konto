package com.example.projektkonto.repository;

import com.example.projektkonto.model.AccountData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {
    @Test
    void randomaccountIdTest(){
        var x = new AccountRepository();
        AccountData accountData = x.save("wegjak123", "konto walutowe", "Nazwa mojego konta");
        x.accountmap.get(accountData.getAccountNumber());
        assertEquals(x.accountmap.get(accountData.getAccountNumber()), accountData);
    }

}