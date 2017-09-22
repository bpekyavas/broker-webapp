package com.aspone.brokerwebapp.application.controller;

import com.aspone.brokerwebapp.application.model.request.TradeRequest;
import com.aspone.brokerwebapp.application.model.response.TradeListResponse;

public interface TradeController {

    void match(TradeRequest tradeRequest);
    TradeListResponse retrieveTradesByTrader(Long traderId);
    TradeListResponse retrieveTrades();
}
