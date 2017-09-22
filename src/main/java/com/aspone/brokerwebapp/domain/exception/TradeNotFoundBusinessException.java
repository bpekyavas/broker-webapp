package com.aspone.brokerwebapp.domain.exception;

public class TradeNotFoundBusinessException extends RuntimeException {

    public TradeNotFoundBusinessException(String message) {
        super(message);
    }
}
