package com.aspone.brokerwebapp.application.converter;

import com.aspone.brokerwebapp.application.model.response.PriceResponse;
import com.aspone.brokerwebapp.domain.vo.PriceVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PriceVoToResponseConverterTest {

    @InjectMocks
    private PriceVoToResponseConverter priceVoToResponseConverter;

    @Test
    public void should_convert(){
        //given
        PriceVo priceVo = new PriceVo();
        priceVo.setId(1L);
        priceVo.setSymbol("GARAN.E");
        priceVo.setPrice(BigDecimal.TEN);
        priceVo.setBid(new BigDecimal("9"));
        priceVo.setOffer(new BigDecimal("11"));

        //when
        PriceResponse priceResponse = priceVoToResponseConverter.convert(priceVo);

        //then
        assertThat(priceResponse.getBid()).isEqualTo(new BigDecimal("9"));
        assertThat(priceResponse.getOffer()).isEqualTo(new BigDecimal("11"));
        assertThat(priceResponse.getSymbol()).isEqualTo("GARAN.E");
    }

}