package com.mse.brokerwebapp.application.model.request;

import com.mse.brokerwebapp.domain.entity.enumtype.Side;

public class TradeRequest {

    private Long securityId;
    private Long traderId;
    private Long quantity;
    private Side side;

    public Long getSecurityId() {
        return securityId;
    }

    public void setSecurityId(Long securityId) {
        this.securityId = securityId;
    }

    public Long getTraderId() {
        return traderId;
    }

    public void setTraderId(Long traderId) {
        this.traderId = traderId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }
}
