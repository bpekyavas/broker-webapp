package com.aspone.brokerwebapp.domain.service;

import com.aspone.brokerwebapp.application.converter.SecurityToPriceVoConverter;
import com.aspone.brokerwebapp.domain.entity.Security;
import com.aspone.brokerwebapp.domain.exception.SecurityNotFoundBusinessException;
import com.aspone.brokerwebapp.domain.repository.SecurityRepository;
import com.aspone.brokerwebapp.domain.vo.PriceVo;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    private SecurityToPriceVoConverter securityToPriceVoConverter;

    @Mock
    private SecurityRepository securityRepository;

    @Test
    public void should_retrieve_price_info() {
        //given
        Long securityId = 1L;
        Security security = new Security();
        security.setId(securityId);
        PriceVo priceVo = new PriceVo();
        priceVo.setId(1L);

        when(securityRepository.findOne(securityId)).thenReturn(security);
        when(securityToPriceVoConverter.convert(security)).thenReturn(priceVo);

        //when
        PriceVo actualPriceVo = priceService.retrievePriceInfo(securityId);

        //then
        assertThat(priceVo.getId()).isEqualTo(1L);
    }

    @Test
    public void should_throw_exception_when_security_id_is_not_found() {
        //given
        when(securityRepository.findOne(1L)).thenReturn(null);

        //when
        Throwable thrown = catchThrowable(() -> priceService.retrievePriceInfo(1L));

        // then
        assertThat(thrown).isInstanceOf(SecurityNotFoundBusinessException.class);
    }

    @Test
    public void should_retrieve_price_list() {
        //given
        Long securityId = 1L;
        Security security = new Security();
        security.setId(securityId);
        List<Security> securityList = Lists.newArrayList(security);
        PriceVo priceVo = new PriceVo();
        priceVo.setId(1L);

        when(securityRepository.findAll()).thenReturn(securityList);
        when(securityToPriceVoConverter.convert(security)).thenReturn(priceVo);

        //when
        List<PriceVo> priceVoList = priceService.retrievePriceList();

        //then
        assertThat(priceVoList).hasSize(1);
    }

    @Test
    public void should_throw_exception_when_no_security_is_present() {
        //given
        when(securityRepository.findAll()).thenReturn(new ArrayList<>());

        //when
        Throwable thrown = catchThrowable(() -> priceService.retrievePriceList());

        // then
        assertThat(thrown).isInstanceOf(SecurityNotFoundBusinessException.class);
    }

    @Test
    public void should_update_spread() {
        //given
        ArgumentCaptor<Security> argumentCaptor = ArgumentCaptor.forClass(Security.class);
        Long securityId = 1L;
        Security security = new Security();
        security.setId(securityId);
        PriceVo priceVo = new PriceVo();
        priceVo.setId(1L);

        when(securityRepository.findOne(securityId)).thenReturn(security);

        //when
        priceService.updateSpread(securityId, new BigDecimal("0.5"));

        //then
        verify(securityRepository).save(argumentCaptor.capture());
        assertThat(priceVo.getId()).isEqualTo(1L);
        assertThat(argumentCaptor.getValue().getSpread()).isEqualTo(new BigDecimal("0.5"));
    }

    @Test
    public void should_throw_exception_during_spread_update_when_security_id_is_not_found() {
        //given
        when(securityRepository.findOne(1L)).thenReturn(null);

        //when
        Throwable thrown = catchThrowable(() -> priceService.updateSpread(1L, new BigDecimal("0.5")));

        // then
        assertThat(thrown).isInstanceOf(SecurityNotFoundBusinessException.class);
    }
}