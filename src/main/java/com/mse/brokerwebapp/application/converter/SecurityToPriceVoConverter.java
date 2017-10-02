package com.mse.brokerwebapp.application.converter;

import com.mse.brokerwebapp.domain.entity.Security;
import com.mse.brokerwebapp.domain.vo.PriceVo;
import org.springframework.stereotype.Component;

@Component
public class SecurityToPriceVoConverter {

    public PriceVo convert(Security security) {
        PriceVo priceVo = new PriceVo();
        priceVo.setId(security.getId());
        priceVo.setPrice(security.getPrice());
        priceVo.setSpread(security.getSpread());
        priceVo.setOffer(security.getOffer());
        priceVo.setBid(security.getBid());
        priceVo.setSymbol(security.getSymbol());

        return priceVo;
    }
}
