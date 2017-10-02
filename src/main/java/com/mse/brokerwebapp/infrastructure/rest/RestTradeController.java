package com.mse.brokerwebapp.infrastructure.rest;

import com.mse.brokerwebapp.application.controller.TradeController;
import com.mse.brokerwebapp.application.model.request.TradeRequest;
import com.mse.brokerwebapp.application.model.response.TradeListResponse;
import com.mse.brokerwebapp.domain.service.TradeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestTradeController implements TradeController {

    private TradeService tradeService;

    public RestTradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @Override
    @GetMapping("/api/v1/trades")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public TradeListResponse retrieveTrades() {
        return tradeService.retrieveAllTrades();
    }

    @Override
    @PostMapping("/api/v1/trades")
    @ResponseStatus(HttpStatus.CREATED)
    public void match(@RequestBody TradeRequest tradeRequest) {
        tradeService.match(tradeRequest);
    }

    @Override
    @GetMapping("/api/v1/trades/{traderId}")
    @ResponseStatus(HttpStatus.OK)
    public TradeListResponse retrieveTradesByTrader(@PathVariable Long traderId) {
        return tradeService.retrieveTrades(traderId);
    }
}
