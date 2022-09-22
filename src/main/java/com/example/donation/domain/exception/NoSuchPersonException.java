package com.example.donation.domain.exception;

public class NoSuchPersonException extends RuntimeException{
    public NoSuchPersonException() {
        super("No such person");
    }

    public NoSuchPersonException(String message) {
        super(message);
    }
}
