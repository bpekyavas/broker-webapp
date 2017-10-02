package com.mse.brokerwebapp.domain.exception;

public class TradesNotFoundBusinessException extends RuntimeException {

    public TradesNotFoundBusinessException(String message) {
        super(message);
    }
}
