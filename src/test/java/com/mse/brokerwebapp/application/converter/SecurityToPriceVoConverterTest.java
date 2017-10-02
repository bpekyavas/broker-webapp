package com.mse.brokerwebapp.application.converter;

import com.mse.brokerwebapp.domain.entity.Security;
import com.mse.brokerwebapp.domain.vo.PriceVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SecurityToPriceVoConverterTest {

    @InjectMocks
    private SecurityToPriceVoConverter securityToPriceVoConverter;

    @Test
    public void should_convert()
    {
        //given
        Security security = new Security();
        security.setId(1L);
        security.setSymbol("GARAN.E");
        security.setPrice(BigDecimal.ONE);
        security.setSpread(new BigDecimal("0.5"));

        //when
        PriceVo priceVo = securityToPriceVoConverter.convert(security);

        //then
        assertThat(priceVo.getId()).isEqualTo(1L);
        assertThat(priceVo.getSymbol()).isEqualTo("GARAN.E");
        assertThat(priceVo.getPrice()).isEqualTo(BigDecimal.ONE);
        assertThat(priceVo.getSpread()).isEqualTo(new BigDecimal("0.5"));
        assertThat(priceVo.getBid()).isEqualTo(new BigDecimal("0.5"));
        assertThat(priceVo.getOffer()).isEqualTo(new BigDecimal("1.5"));
    }
}