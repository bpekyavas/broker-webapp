package com.aspone.brokerwebapp.domain.service;

import com.aspone.brokerwebapp.application.converter.TradeListToResponseConverter;
import com.aspone.brokerwebapp.application.model.response.TradeListResponse;
import com.aspone.brokerwebapp.domain.entity.Trade;
import com.aspone.brokerwebapp.domain.entity.Trader;
import com.aspone.brokerwebapp.domain.exception.TradeNotFoundBusinessException;
import com.aspone.brokerwebapp.domain.exception.TradeSaveBusinessException;
import com.aspone.brokerwebapp.domain.exception.TraderNotFoundBusinessException;
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
    private TradeListToResponseConverter tradeListToResponseConverter;

    public TradeService(TradeRepository tradeRepository, TraderRepository traderRepository, TradeListToResponseConverter tradeListToResponseConverter) {
        this.tradeRepository = tradeRepository;
        this.traderRepository = traderRepository;
        this.tradeListToResponseConverter = tradeListToResponseConverter;
    }

    public void match(Long buyerId, BigDecimal price, Long quantity) {
        Trade trade = createTrade(buyerId, price, quantity);
        try {
            tradeRepository.save(trade);
        } catch (Exception e) {
            throw new TradeSaveBusinessException("Trade could not be saved!");
        }
    }

    private Trade createTrade(Long buyerId, BigDecimal price, Long quantity) {
        Trader trader = Optional.ofNullable(traderRepository.findOne(buyerId))
                .orElseThrow(() -> new TraderNotFoundBusinessException("Trader is not found!"));
        Trade trade = new Trade();
        trade.setBuyer(trader);
        trade.setDate(new Date());
        trade.setPrice(price);
        trade.setQuantity(quantity);

        return trade;
    }

    public TradeListResponse retrieveTrades(Long traderId) {
        Trader trader = Optional.ofNullable(traderRepository.findOne(traderId))
                .orElseThrow(() -> new TraderNotFoundBusinessException("Trader is not found!"));
        List<Trade> tradeList = Optional.ofNullable(tradeRepository.findAllByBuyer(trader))
                .orElseThrow(() -> new TradeNotFoundBusinessException("Trade is not found!"));
        return tradeListToResponseConverter.convert(tradeList);
    }

    public TradeListResponse retrieveAllTrades() {
        List<Trade> tradeList = Optional.ofNullable(tradeRepository.findAll())
                .orElseThrow(() -> new TradeNotFoundBusinessException("Trade is not found!"));
        return tradeListToResponseConverter.convert(tradeList);
    }
}
