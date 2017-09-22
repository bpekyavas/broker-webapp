package com.aspone.brokerwebapp.application.converter;

import com.aspone.brokerwebapp.domain.entity.Security;
import com.aspone.brokerwebapp.domain.vo.PriceVo;
import org.springframework.stereotype.Component;

@Component
public class SecurityToPriceVoConverter {

    public PriceVo convert(Security security) {
        PriceVo priceVo = new PriceVo();
        priceVo.setPrice(security.getRate());
        priceVo.setBestAsk(security.getRate().add(security.getSpread()));
        priceVo.setBestBid(security.getRate().subtract(security.getSpread()));

        return priceVo;
    }
}
