package com.tech.task.exception;

public class DriverNotFoundException extends RuntimeException {

    public DriverNotFoundException(String exception) {
        super(exception);
    }
}