package com.aspone.brokerwebapp.application.model.dto;

import java.math.BigDecimal;

public class PriceDto {

    private String symbol;
    private BigDecimal bid;
    private BigDecimal offer;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getOffer() {
        return offer;
    }

    public void setOffer(BigDecimal offer) {
        this.offer = offer;
    }
}
