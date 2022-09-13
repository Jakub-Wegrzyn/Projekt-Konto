package com.example.projektkonto.service;

import com.example.projektkonto.response.Error;

public class ErrorService {
    public Error accountDataNotfound(String message, Object object, int status){
        Error errorResponse = new Error(message,object,status);
        return errorResponse;
    }

}
