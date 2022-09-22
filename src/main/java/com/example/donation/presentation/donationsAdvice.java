package com.example.donation.presentation;

import com.example.donation.domain.exception.NoSuchDonationException;
import com.example.donation.domain.exception.NoSuchPersonException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class donationsAdvice {

    @ExceptionHandler(NoSuchDonationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNoSuchDonationException(NoSuchDonationException exception) {
        return new ApiError(exception.getMessage());
    }

    @ExceptionHandler(NoSuchPersonException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNoSuchPersonException(NoSuchPersonException exception) {
        return new ApiError(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleInvalidArguments(MethodArgumentNotValidException exception) {
        return new ApiError(exception.getMessage());
    }
}
