package com.aspone.brokerwebapp.application.converter;

import com.aspone.brokerwebapp.application.model.dto.PriceDto;
import com.aspone.brokerwebapp.application.model.response.PriceListResponse;
import com.aspone.brokerwebapp.domain.vo.PriceVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceVoListToResponseConverter {

    public PriceListResponse convert(List<PriceVo> priceVoList) {
        List<PriceDto> priceDtoList = priceVoList.stream().map(priceVo -> {
            PriceDto priceDto = new PriceDto();
            priceDto.setSecurityCode(priceVo.getSecurityCode());
            priceDto.setBestAsk(priceVo.getBestAsk());
            priceDto.setBestBid(priceVo.getBestBid());
            priceDto.setPrice(priceVo.getPrice());

            return priceDto;
        }).collect(Collectors.toList());

        return getPriceListResponse(priceDtoList);
    }

    private PriceListResponse getPriceListResponse(List<PriceDto> priceDtoList) {
        PriceListResponse priceListResponse = new PriceListResponse();
        priceListResponse.setPriceDtoList(priceDtoList);
        return priceListResponse;
    }
}
