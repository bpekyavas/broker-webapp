package com.aspone.brokerwebapp.application.converter;

import com.aspone.brokerwebapp.application.model.response.PriceResponse;
import com.aspone.brokerwebapp.domain.vo.PriceVo;
import org.springframework.stereotype.Component;

@Component
public class PriceVoToResponseConverter {

    public PriceResponse convert(PriceVo priceVo) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setSymbol(priceVo.getSymbol());
        priceResponse.setOffer(priceVo.getOffer());
        priceResponse.setBid(priceVo.getBid());

        return priceResponse;
    }
}
