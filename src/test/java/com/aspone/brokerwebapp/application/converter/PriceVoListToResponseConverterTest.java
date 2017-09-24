package com.aspone.brokerwebapp.application.converter;

import com.aspone.brokerwebapp.application.model.dto.PriceDto;
import com.aspone.brokerwebapp.application.model.response.PriceListResponse;
import com.aspone.brokerwebapp.domain.vo.PriceVo;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PriceVoListToResponseConverterTest {

    @InjectMocks
    private PriceVoListToResponseConverter priceVoListToResponseConverter;

    @Test
    public void should_convert() {
        //given
        PriceVo firstPriceVo = new PriceVo();
        firstPriceVo.setId(1L);
        firstPriceVo.setSymbol("GARAN.E");
        firstPriceVo.setPrice(BigDecimal.TEN);
        firstPriceVo.setSpread(BigDecimal.ONE);
        firstPriceVo.setBid(new BigDecimal("9"));
        firstPriceVo.setOffer(new BigDecimal("11"));

        PriceVo secondPriceVo = new PriceVo();
        secondPriceVo.setId(2L);
        secondPriceVo.setSymbol("AKBNK.E");
        secondPriceVo.setPrice(new BigDecimal("20"));
        secondPriceVo.setSpread(new BigDecimal("2"));
        secondPriceVo.setBid(new BigDecimal("18"));
        secondPriceVo.setOffer(new BigDecimal("22"));

        List<PriceVo> priceVoList = Lists.newArrayList(firstPriceVo, secondPriceVo);

        //when
        PriceListResponse priceListResponse = priceVoListToResponseConverter.convert(priceVoList);

        //then
        assertThat(priceListResponse).isNotNull();

        List<PriceDto> priceDtoList = priceListResponse.getPriceDtoList();
        assertThat(priceDtoList).isNotEmpty();
        assertThat(priceDtoList).hasSize(2);

        PriceDto firstPriceDto = priceDtoList.get(0);
        assertThat(firstPriceDto.getId()).isEqualTo(1L);
        assertThat(firstPriceDto.getBid()).isEqualTo(new BigDecimal("9"));
        assertThat(firstPriceDto.getSpread()).isEqualTo(new BigDecimal("1"));
        assertThat(firstPriceDto.getOffer()).isEqualTo(new BigDecimal("11"));
        assertThat(firstPriceDto.getSymbol()).isEqualTo("GARAN.E");

        PriceDto secondPriceDto = priceDtoList.get(1);
        assertThat(secondPriceDto.getId()).isEqualTo(2L);
        assertThat(secondPriceDto.getBid()).isEqualTo(new BigDecimal("18"));
        assertThat(secondPriceDto.getSpread()).isEqualTo(new BigDecimal("2"));
        assertThat(secondPriceDto.getOffer()).isEqualTo(new BigDecimal("22"));
        assertThat(secondPriceDto.getSymbol()).isEqualTo("AKBNK.E");
    }
}