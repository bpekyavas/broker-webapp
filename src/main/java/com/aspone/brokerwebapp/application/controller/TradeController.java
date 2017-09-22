package com.aspone.brokerwebapp.application.controller;

import com.aspone.brokerwebapp.application.model.response.TradeListResponse;

import java.math.BigDecimal;

public interface TradeController {

    void match(Long buyerId, BigDecimal price, Long quantity);
    TradeListResponse retrieveTradesByTrader(Long buyerId);
    TradeListResponse retrieveTrades();
}
