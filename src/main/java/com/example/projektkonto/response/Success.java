package com.example.projektkonto.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Success {
    private String message;
    private Object object;
    private String status;

    public Success(String message, Object object, String status){
        this.message = message;
        this.object = object;
        this.status = status;
    }

    public Success(Success success){
        this.message = success.message;
        this.status = success.status;
        this.object = success.object;
    }
    public Success(String x){
        this.message = x;
    }


    public Object getObject() {
        return object;
    }
    public String getMessage(){
        return message;
    }
    public String status(){
        return status;
    }


}
