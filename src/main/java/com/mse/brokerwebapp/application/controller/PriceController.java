package com.mse.brokerwebapp.application.controller;

import com.mse.brokerwebapp.application.model.request.UpdateSpreadRequest;
import com.mse.brokerwebapp.application.model.response.PriceListResponse;
import com.mse.brokerwebapp.application.model.response.PriceResponse;

public interface PriceController {

    PriceResponse retrievePrice(Long securityId);
    PriceListResponse retrieveAllPrices();
    void updateSpread(Long securityId, UpdateSpreadRequest updateSpreadRequest);
}
