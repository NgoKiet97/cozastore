package com.cybersoft.cozastore.exception;

public class UserNotFoundException extends RuntimeException{
    private String message;
    private int statusCode;

    public UserNotFoundException(String message, int statusCode){
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
