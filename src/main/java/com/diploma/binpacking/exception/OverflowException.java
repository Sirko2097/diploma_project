package com.diploma.binpacking.exception;

public class OverflowException extends Exception {
    @Override
    public String getMessage() {
        return "Overflow!";
    }
}
