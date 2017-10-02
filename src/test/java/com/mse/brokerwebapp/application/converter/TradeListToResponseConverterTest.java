package com.mse.brokerwebapp.application.converter;

import com.mse.brokerwebapp.application.model.dto.TradeDto;
import com.mse.brokerwebapp.application.model.response.TradeListResponse;
import com.mse.brokerwebapp.domain.entity.Security;
import com.mse.brokerwebapp.domain.entity.Trade;
import com.mse.brokerwebapp.domain.entity.Trader;
import com.mse.brokerwebapp.domain.entity.enumtype.Side;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TradeListToResponseConverterTest {

    @InjectMocks
    private TradeListToResponseConverter tradeListToResponseConverter;

    @Test
    public void should_convert() {
        //given
        Security security = new Security();
        security.setSymbol("GARAN.E");
        Trader trader = new Trader();
        trader.setName("Safa Ertekin");
        Date tradeDate = new Date();

        Trade firstTrade = new Trade();
        firstTrade.setId(1L);
        firstTrade.setQuantity(100L);
        firstTrade.setDate(tradeDate);
        firstTrade.setSecurity(security);
        firstTrade.setTrader(trader);
        firstTrade.setPrice(BigDecimal.TEN);
        firstTrade.setSide(Side.BUY);

        Trade secondTrade = new Trade();
        secondTrade.setId(2L);
        secondTrade.setQuantity(200L);
        secondTrade.setDate(tradeDate);
        secondTrade.setSecurity(security);
        secondTrade.setTrader(trader);
        secondTrade.setPrice(new BigDecimal("20"));
        secondTrade.setSide(Side.SELL);

        List<Trade> tradeList = Lists.newArrayList(firstTrade, secondTrade);

        //when
        TradeListResponse tradeListResponse = tradeListToResponseConverter.convert(tradeList);

        //then
        assertThat(tradeListResponse).isNotNull();

        List<TradeDto> tradeDtoList = tradeListResponse.getTradeDtoList();
        assertThat(tradeDtoList).isNotEmpty();
        assertThat(tradeDtoList).hasSize(2);

        TradeDto firstTradeDto = tradeDtoList.get(0);
        assertThat(firstTradeDto.getId()).isEqualTo(1L);
        assertThat(firstTradeDto.getPrice()).isEqualTo(BigDecimal.TEN);
        assertThat(firstTradeDto.getQuantity()).isEqualTo(100L);
        assertThat(firstTradeDto.getSymbol()).isEqualTo("GARAN.E");
        assertThat(firstTradeDto.getSide()).isEqualTo(Side.BUY);
        assertThat(firstTradeDto.getTraderName()).isEqualTo("Safa Ertekin");
        assertThat(firstTradeDto.getDate()).isEqualTo(tradeDate);


        TradeDto secondTradeDto = tradeDtoList.get(1);
        assertThat(secondTradeDto.getId()).isEqualTo(2L);
        assertThat(secondTradeDto.getPrice()).isEqualTo(new BigDecimal("20"));
        assertThat(secondTradeDto.getQuantity()).isEqualTo(200L);
        assertThat(secondTradeDto.getSymbol()).isEqualTo("GARAN.E");
        assertThat(secondTradeDto.getSide()).isEqualTo(Side.SELL);
        assertThat(secondTradeDto.getTraderName()).isEqualTo("Safa Ertekin");
        assertThat(secondTradeDto.getDate()).isEqualTo(tradeDate);
    }
}