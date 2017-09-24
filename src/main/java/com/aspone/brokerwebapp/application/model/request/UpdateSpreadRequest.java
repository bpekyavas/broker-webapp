package com.aspone.brokerwebapp.application.model.request;

import java.math.BigDecimal;

public class UpdateSpreadRequest {

    BigDecimal spread;

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }
}
