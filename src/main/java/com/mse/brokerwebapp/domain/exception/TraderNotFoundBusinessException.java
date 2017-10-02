package com.mse.brokerwebapp.domain.exception;

public class TraderNotFoundBusinessException extends RuntimeException{

    public TraderNotFoundBusinessException(String message) {
        super(message);
    }
}
