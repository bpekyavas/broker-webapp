package com.mse.brokerwebapp.domain.service;

import com.mse.brokerwebapp.application.converter.TradeListToResponseConverter;
import com.mse.brokerwebapp.application.model.request.TradeRequest;
import com.mse.brokerwebapp.domain.entity.Security;
import com.mse.brokerwebapp.domain.entity.Trade;
import com.mse.brokerwebapp.domain.entity.Trader;
import com.mse.brokerwebapp.domain.entity.enumtype.Side;
import com.mse.brokerwebapp.domain.exception.TraderNotFoundBusinessException;
import com.mse.brokerwebapp.domain.exception.TradesNotFoundBusinessException;
import com.mse.brokerwebapp.domain.repository.SecurityRepository;
import com.mse.brokerwebapp.domain.repository.TradeRepository;
import com.mse.brokerwebapp.domain.repository.TraderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceTest {

    @InjectMocks
    private TradeService tradeService;

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private TraderRepository traderRepository;

    @Mock
    private SecurityRepository securityRepository;

    @Mock
    private TradeListToResponseConverter tradeListToResponseConverter;

    @Test
    public void should_match() {
        //given
        ArgumentCaptor<Trade> argumentCaptor = ArgumentCaptor.forClass(Trade.class);
        Security security = new Security();
        security.setId(1L);
        security.setPrice(BigDecimal.TEN);
        security.setSpread(new BigDecimal("0.5"));
        Trader trader = new Trader();
        trader.setId(1L);
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setSecurityId(1L);
        tradeRequest.setQuantity(100L);
        tradeRequest.setSide(Side.BUY);
        tradeRequest.setTraderId(1L);

        when(securityRepository.findOne(1L)).thenReturn(security);
        when(traderRepository.findOne(1L)).thenReturn(trader);

        //when
        tradeService.match(tradeRequest);

        //then
        verify(tradeRepository).save(argumentCaptor.capture());
        Trade trade = argumentCaptor.getValue();
        assertThat(trade.getPrice()).isEqualTo(new BigDecimal("10.5"));
        assertThat(trade.getQuantity()).isEqualTo(100L);
        assertThat(trade.getSide()).isEqualTo(Side.BUY);
        assertThat(trade.getSecurity().getId()).isEqualTo(1L);
        assertThat(trade.getTrader().getId()).isEqualTo(1L);
    }

    @Test
    public void should_throw_exception_when_trader_is_not_found() {
        //given
        Security security = new Security();
        security.setId(1L);
        security.setPrice(BigDecimal.TEN);
        security.setSpread(new BigDecimal("0.5"));
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setSecurityId(1L);
        tradeRequest.setTraderId(1L);
        tradeRequest.setSide(Side.BUY);

        when(securityRepository.findOne(1L)).thenReturn(security);
        when(traderRepository.findOne(1L)).thenReturn(null);

        //when
        Throwable thrown = catchThrowable(() -> tradeService.match(tradeRequest));

        //then
        assertThat(thrown).isInstanceOf(TraderNotFoundBusinessException.class);
    }

    @Test
    public void should_throw_exception_when_trade_is_not_found() {
        //given
        Trader trader = new Trader();
        trader.setId(1L);

        when(traderRepository.findOne(1L)).thenReturn(trader);
        when(tradeRepository.findAllByTrader(trader)).thenReturn(new ArrayList<>());

        //when
        Throwable thrown = catchThrowable(() -> tradeService.retrieveTrades(trader.getId()));

        //then
        assertThat(thrown).isInstanceOf(TradesNotFoundBusinessException.class);
    }

    @Test
    public void should_throw_exception_while_retrieving_all_trades_when_trade_is_not_found() {
        //given
        when(tradeRepository.findAll()).thenReturn(new ArrayList<>());

        //when
        Throwable thrown = catchThrowable(() -> tradeService.retrieveAllTrades());

        //then
        assertThat(thrown).isInstanceOf(TradesNotFoundBusinessException.class);
    }
}