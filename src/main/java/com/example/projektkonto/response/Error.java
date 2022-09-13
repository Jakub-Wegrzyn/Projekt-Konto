package com.example.projektkonto.response;

public class Error {
    private String message;
    private Object object;
    private int status;

    public Error(String message, Object object, int status){
        this.message = message;
        this.object = object;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }


}
