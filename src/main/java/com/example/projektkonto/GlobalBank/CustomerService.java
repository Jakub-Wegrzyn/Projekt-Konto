package com.example.projektkonto.GlobalBank;

public interface CustomerService {
    boolean hasCustomerFile(
            String personalId,
            String firstName,
            String lastName);
}
