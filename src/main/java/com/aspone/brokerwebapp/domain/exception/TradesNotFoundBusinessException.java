package com.aspone.brokerwebapp.domain.exception;

public class TradesNotFoundBusinessException extends RuntimeException {

    public TradesNotFoundBusinessException(String message) {
        super(message);
    }
}
