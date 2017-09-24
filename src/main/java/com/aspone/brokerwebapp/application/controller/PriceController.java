package com.aspone.brokerwebapp.application.controller;

import com.aspone.brokerwebapp.application.model.request.UpdateSpreadRequest;
import com.aspone.brokerwebapp.application.model.response.PriceListResponse;
import com.aspone.brokerwebapp.application.model.response.PriceResponse;

public interface PriceController {

    PriceResponse retrievePrice(Long securityId);
    PriceListResponse retrieveAllPrices();
    void updateSpread(Long securityId, UpdateSpreadRequest updateSpreadRequest);
}
