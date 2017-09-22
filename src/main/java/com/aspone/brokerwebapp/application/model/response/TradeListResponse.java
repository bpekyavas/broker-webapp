package com.aspone.brokerwebapp.application.model.response;

import com.aspone.brokerwebapp.application.model.dto.TradeDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TradeListResponse {

    @JsonProperty("trades")
    private List<TradeDto> tradeDtoList;

    public List<TradeDto> getTradeDtoList() {
        return tradeDtoList;
    }

    public void setTradeDtoList(List<TradeDto> tradeDtoList) {
        this.tradeDtoList = tradeDtoList;
    }
}
