package com.aspone.brokerwebapp.domain.vo;

import java.math.BigDecimal;

public class PriceVo {

    private BigDecimal price;
    private BigDecimal bestBid;
    private BigDecimal bestAsk;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getBestBid() {
        return bestBid;
    }

    public void setBestBid(BigDecimal bestBid) {
        this.bestBid = bestBid;
    }

    public BigDecimal getBestAsk() {
        return bestAsk;
    }

    public void setBestAsk(BigDecimal bestAsk) {
        this.bestAsk = bestAsk;
    }
}
