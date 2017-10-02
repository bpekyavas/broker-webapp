package com.mse.brokerwebapp.application.converter;

import com.mse.brokerwebapp.application.model.response.PriceResponse;
import com.mse.brokerwebapp.domain.vo.PriceVo;
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
