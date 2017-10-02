package com.mse.brokerwebapp.application.model.response;

import com.mse.brokerwebapp.application.model.dto.PriceDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PriceListResponse {

    @JsonProperty("prices")
    List<PriceDto> priceDtoList;

    public List<PriceDto> getPriceDtoList() {
        return priceDtoList;
    }

    public void setPriceDtoList(List<PriceDto> priceDtoList) {
        this.priceDtoList = priceDtoList;
    }
}
