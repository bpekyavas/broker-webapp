package com.aspone.brokerwebapp.domain.vo;

import java.math.BigDecimal;

public class PriceVo {

    private Long id;
    private String symbol;
    private BigDecimal price;
    private BigDecimal spread;
    private BigDecimal bid;
    private BigDecimal offer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }
}
