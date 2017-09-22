package com.aspone.brokerwebapp.domain.service;

import com.aspone.brokerwebapp.application.converter.TradeListToResponseConverter;
import com.aspone.brokerwebapp.application.model.request.TradeRequest;
import com.aspone.brokerwebapp.application.model.response.TradeListResponse;
import com.aspone.brokerwebapp.domain.entity.Security;
import com.aspone.brokerwebapp.domain.entity.Trade;
import com.aspone.brokerwebapp.domain.entity.Trader;
import com.aspone.brokerwebapp.domain.entity.enumtype.Side;
import com.aspone.brokerwebapp.domain.exception.*;
import com.aspone.brokerwebapp.domain.repository.SecurityRepository;
import com.aspone.brokerwebapp.domain.repository.TradeRepository;
import com.aspone.brokerwebapp.domain.repository.TraderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
        Security security = Optional.ofNullable(securityRepository.findOne(tradeRequest.getSecurityId()))
                .orElseThrow(() -> new SecurityNotFoundBusinessException("Security is not found!"));
        BigDecimal price = Side.BUY.equals(tradeRequest.getSide()) ? security.getBestAsk() : security.getBestBid();
        saveTrade(tradeRequest, price);
        security.setPrice(price);
        try {
            securityRepository.save(security);
        } catch (Exception e) {
            throw new SecurityUpdateBusinessException("Security price could not be updated");
        }
    }

    private void saveTrade(TradeRequest tradeRequest, BigDecimal price) {
        Trade trade = createTrade(tradeRequest,price);
        try {
            tradeRepository.save(trade);
        } catch (Exception e) {
            throw new TradeSaveBusinessException("Trade could not be saved!");
        }
    }

    private Trade createTrade(TradeRequest tradeRequest, BigDecimal price) {
        Security security = Optional.ofNullable(securityRepository.findOne(tradeRequest.getSecurityId()))
                .orElseThrow(() -> new SecurityNotFoundBusinessException("Security is not found!"));
        Trader trader = Optional.ofNullable(traderRepository.findOne(tradeRequest.getTraderId()))
                .orElseThrow(() -> new TraderNotFoundBusinessException("Trader is not found!"));

        Trade trade = new Trade();
        trade.setSecurity(security);
        trade.setTrader(trader);
        trade.setDate(new Date());
        trade.setPrice(price);
        trade.setQuantity(tradeRequest.getQuantity());
        trade.setSide(tradeRequest.getSide());

        return trade;
    }

    public TradeListResponse retrieveTrades(Long traderId) {
        Trader trader = Optional.ofNullable(traderRepository.findOne(traderId))
                .orElseThrow(() -> new TraderNotFoundBusinessException("Trader is not found!"));
        List<Trade> tradeList = Optional.ofNullable(tradeRepository.findAllByTrader(trader))
                .orElseThrow(() -> new TradeNotFoundBusinessException("Trade is not found!"));
        return tradeListToResponseConverter.convert(tradeList);
    }

    public TradeListResponse retrieveAllTrades() {
        List<Trade> tradeList = Optional.ofNullable(tradeRepository.findAll())
                .orElseThrow(() -> new TradeNotFoundBusinessException("Trade is not found!"));
        return tradeListToResponseConverter.convert(tradeList);
    }
}
