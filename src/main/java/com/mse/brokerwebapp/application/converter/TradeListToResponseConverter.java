package com.mse.brokerwebapp.application.converter;

import com.mse.brokerwebapp.application.model.dto.TradeDto;
import com.mse.brokerwebapp.application.model.response.TradeListResponse;
import com.mse.brokerwebapp.domain.entity.Trade;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TradeListToResponseConverter {

    public TradeListResponse convert(List<Trade> tradeList) {
        List<TradeDto> tradeDtoList = tradeList.stream().map(trade -> {
            TradeDto tradeDto = new TradeDto();
            tradeDto.setDate(trade.getDate());
            tradeDto.setId(trade.getId());
            tradeDto.setPrice(trade.getPrice());
            tradeDto.setQuantity(trade.getQuantity());
            tradeDto.setSymbol(trade.getSecurity().getSymbol());
            tradeDto.setTraderName(trade.getTrader().getName());
            tradeDto.setSide(trade.getSide());
            return tradeDto;
        }).collect(Collectors.toList());
        TradeListResponse tradeListResponse = new TradeListResponse();
        tradeListResponse.setTradeDtoList(tradeDtoList);
        return tradeListResponse;
    }
}
