package com.cybersoft.cozastore_java21.exception;

public class CustomFileNotFoundException extends RuntimeException{
    private int status;
    private String message;

    public CustomFileNotFoundException() {
    }

    public CustomFileNotFoundException(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
