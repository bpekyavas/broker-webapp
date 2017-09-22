package com.aspone.brokerwebapp.application.converter;

import com.aspone.brokerwebapp.application.model.dto.TradeDto;
import com.aspone.brokerwebapp.application.model.dto.TraderDto;
import com.aspone.brokerwebapp.application.model.response.TradeListResponse;
import com.aspone.brokerwebapp.domain.entity.Trade;
import com.aspone.brokerwebapp.domain.entity.Trader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TradeListToResponseConverter {

    public TradeListResponse convert(List<Trade> tradeList) {
        List<TradeDto>  tradeDtoList = tradeList.stream().map(trade -> {
            TradeDto tradeDto = new TradeDto();
            tradeDto.setDate(trade.getDate());
            tradeDto.setId(trade.getId());
            tradeDto.setPrice(trade.getPrice());
            tradeDto.setQuantity(trade.getQuantity());
            tradeDto.setBuyer(convertToTraderDto(trade.getBuyer()));
            return tradeDto;
        }).collect(Collectors.toList());
        TradeListResponse tradeListResponse = new TradeListResponse();
        tradeListResponse.setTradeDtoList(tradeDtoList);
        return tradeListResponse;
    }

    private TraderDto convertToTraderDto(Trader buyer) {
        TraderDto traderDto = new TraderDto();
        traderDto.setId(buyer.getId());
        traderDto.setName(buyer.getName());
        return traderDto;
    }
}
