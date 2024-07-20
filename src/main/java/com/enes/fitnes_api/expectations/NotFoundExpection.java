package com.enes.fitnes_api.expectations;

public class NotFoundExpection extends RuntimeException {

    public NotFoundExpection(String message) {
        super(message);
    }

    public NotFoundExpection(String message, Throwable cause) {
        super(message, cause);
    }
}