package com.example.projektkonto.GlobalBank;

import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<?> createAndReturnAccountNumber(
            String personalId,
            String productType,
            String accountName);

    ResponseEntity<?> modifyAccountName(
            String accountNumber,
            String accountName);

    ResponseEntity<?> fetchDetails(String accountNumber);

    ResponseEntity<?> close(String accountNumber);
}
