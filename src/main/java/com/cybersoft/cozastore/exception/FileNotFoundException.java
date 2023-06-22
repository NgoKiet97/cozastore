package com.cybersoft.cozastore.exception;

public class FileNotFoundException {

    private String message;

    public FileNotFoundException(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
