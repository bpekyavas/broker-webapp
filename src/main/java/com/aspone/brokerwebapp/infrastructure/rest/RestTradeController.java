package com.aspone.brokerwebapp.infrastructure.rest;

import com.aspone.brokerwebapp.application.controller.TradeController;
import com.aspone.brokerwebapp.application.model.request.TradeRequest;
import com.aspone.brokerwebapp.application.model.response.TradeListResponse;
import com.aspone.brokerwebapp.domain.service.TradeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestTradeController implements TradeController {

    private TradeService tradeService;

    public RestTradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @Override
    @PostMapping("/api/v1/trades")
    @ResponseStatus(HttpStatus.CREATED)
    public void match(TradeRequest tradeRequest) {
        tradeService.match(tradeRequest);
    }

    @Override
    @GetMapping("/api/v1/trades")
    @ResponseStatus(HttpStatus.OK)
    public TradeListResponse retrieveTrades() {
        return tradeService.retrieveAllTrades();
    }

    @Override
    @GetMapping("/api/v1/trades/{traderId}")
    @ResponseStatus(HttpStatus.OK)
    public TradeListResponse retrieveTradesByTrader(@PathVariable Long traderId) {
        return tradeService.retrieveTrades(traderId);
    }
}
