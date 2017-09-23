package com.aspone.brokerwebapp.domain.service;

import com.aspone.brokerwebapp.application.converter.TradeListToResponseConverter;
import com.aspone.brokerwebapp.application.model.request.TradeRequest;
import com.aspone.brokerwebapp.application.model.response.TradeListResponse;
import com.aspone.brokerwebapp.domain.entity.Security;
import com.aspone.brokerwebapp.domain.entity.Trade;
import com.aspone.brokerwebapp.domain.entity.Trader;
import com.aspone.brokerwebapp.domain.entity.enumtype.Side;
import com.aspone.brokerwebapp.domain.exception.SecurityNotFoundBusinessException;
import com.aspone.brokerwebapp.domain.exception.TradeSaveBusinessException;
import com.aspone.brokerwebapp.domain.exception.TraderNotFoundBusinessException;
import com.aspone.brokerwebapp.domain.exception.TradesNotFoundBusinessException;
import com.aspone.brokerwebapp.domain.repository.SecurityRepository;
import com.aspone.brokerwebapp.domain.repository.TradeRepository;
import com.aspone.brokerwebapp.domain.repository.TraderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    private TradeRepository tradeRepository;
    private TraderRepository traderRepository;
    private SecurityRepository securityRepository;
    private TradeListToResponseConverter tradeListToResponseConverter;

    public TradeService(TradeRepository tradeRepository, TraderRepository traderRepository, SecurityRepository securityRepository, TradeListToResponseConverter tradeListToResponseConverter) {
        this.tradeRepository = tradeRepository;
        this.traderRepository = traderRepository;
        this.securityRepository = securityRepository;
        this.tradeListToResponseConverter = tradeListToResponseConverter;
    }

    public void match(TradeRequest tradeRequest) {
        Trade trade = createTrade(tradeRequest);
        try {
            tradeRepository.save(trade);
        } catch (Exception e) {
            throw new TradeSaveBusinessException("Trade could not be saved!");
        }
    }

    private Trade createTrade(TradeRequest tradeRequest) {
        Security security = Optional.ofNullable(securityRepository.findOne(tradeRequest.getSecurityId()))
                .orElseThrow(() -> new SecurityNotFoundBusinessException("Security is not found!"));
        BigDecimal tradePrice = Side.BUY.equals(tradeRequest.getSide()) ? security.getBestAsk() : security.getBestBid();
        Trader trader = Optional.ofNullable(traderRepository.findOne(tradeRequest.getTraderId()))
                .orElseThrow(() -> new TraderNotFoundBusinessException("Trader is not found!"));

        Trade trade = new Trade();
        trade.setSecurity(security);
        trade.setTrader(trader);
        trade.setDate(new Date());
        trade.setPrice(tradePrice);
        trade.setQuantity(tradeRequest.getQuantity());
        trade.setSide(tradeRequest.getSide());

        return trade;
    }

    public TradeListResponse retrieveTrades(Long traderId) {
        Trader trader = Optional.ofNullable(traderRepository.findOne(traderId))
                .orElseThrow(() -> new TraderNotFoundBusinessException("Trader is not found!"));
        List<Trade> tradeList = tradeRepository.findAllByTrader(trader);
        if (tradeList.isEmpty()) {
            throw new TradesNotFoundBusinessException("No trades found!");
        }
        return tradeListToResponseConverter.convert(tradeList);
    }

    public TradeListResponse retrieveAllTrades() {
        List<Trade> tradeList = tradeRepository.findAll();
        if (tradeList.isEmpty()) {
            throw new TradesNotFoundBusinessException("No trades found!");
        }
        return tradeListToResponseConverter.convert(tradeList);
    }
}
