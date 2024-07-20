package com.enes.fitnes_api.expectations;

public class AllReadyExists extends RuntimeException {

    public AllReadyExists(String message) {
        super(message);
    }

    public AllReadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
