package com.mse.brokerwebapp.application.controller;

import com.mse.brokerwebapp.application.model.request.TradeRequest;
import com.mse.brokerwebapp.application.model.response.TradeListResponse;

public interface TradeController {

    void match(TradeRequest tradeRequest);
    TradeListResponse retrieveTradesByTrader(Long traderId);
    TradeListResponse retrieveTrades();
}
