package com.aspone.brokerwebapp.application.converter;

import com.aspone.brokerwebapp.application.model.response.PriceResponse;
import com.aspone.brokerwebapp.domain.vo.PriceVo;
import org.springframework.stereotype.Component;

@Component
public class PriceVoToResponseConverter {

    public PriceResponse convert(PriceVo priceVo)
    {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setPrice(priceVo.getPrice());
        priceResponse.setBestAsk(priceVo.getBestAsk());
        priceResponse.setBestBid(priceVo.getBestBid());

        return priceResponse;
    }
}
