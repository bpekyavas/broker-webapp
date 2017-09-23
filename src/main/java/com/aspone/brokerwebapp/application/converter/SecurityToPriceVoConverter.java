package com.aspone.brokerwebapp.application.converter;

import com.aspone.brokerwebapp.domain.entity.Security;
import com.aspone.brokerwebapp.domain.vo.PriceVo;
import org.springframework.stereotype.Component;

@Component
public class SecurityToPriceVoConverter {

    public PriceVo convert(Security security) {
        PriceVo priceVo = new PriceVo();
        priceVo.setPrice(security.getPrice());
        priceVo.setBestAsk(security.getBestAsk());
        priceVo.setBestBid(security.getBestBid());
        priceVo.setSecurityCode(security.getCode());

        return priceVo;
    }
}
