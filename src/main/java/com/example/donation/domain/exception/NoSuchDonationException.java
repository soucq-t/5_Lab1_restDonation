package com.example.donation.domain.exception;

public class NoSuchDonationException extends RuntimeException{
    public NoSuchDonationException() {
        super("No such donation");
    }

    public NoSuchDonationException(String message) {
        super(message);
    }
}
