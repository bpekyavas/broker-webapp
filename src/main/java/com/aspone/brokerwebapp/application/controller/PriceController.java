package com.aspone.brokerwebapp.application.controller;

import com.aspone.brokerwebapp.application.model.response.PriceResponse;

import java.math.BigDecimal;

public interface PriceController {

    PriceResponse retrievePrice(Long securityId);
    void updatePrice(Long securityId, BigDecimal price);
}
