package com.aspone.brokerwebapp.infrastructure.rest;

import com.aspone.brokerwebapp.application.controller.PriceController;
import com.aspone.brokerwebapp.application.converter.PriceVoToResponseConverter;
import com.aspone.brokerwebapp.application.model.response.PriceResponse;
import com.aspone.brokerwebapp.domain.service.PriceService;
import com.aspone.brokerwebapp.domain.vo.PriceVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class RestPriceController implements PriceController {

    private PriceService priceService;
    private PriceVoToResponseConverter priceVoToResponseConverter;

    public RestPriceController(PriceService priceService, PriceVoToResponseConverter priceVoToResponseConverter) {
        this.priceService = priceService;
        this.priceVoToResponseConverter = priceVoToResponseConverter;
    }

    @Override
    @GetMapping("/api/v1/prices/{securityId}")
    @ResponseStatus(HttpStatus.OK)
    public PriceResponse retrievePrice(@PathVariable Long securityId) {
        PriceVo priceVo = priceService.retrievePriceInfo(securityId);
        return priceVoToResponseConverter.convert(priceVo);
    }

    @Override
    @PostMapping("/api/v1/prices/{securityId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateSpread(@PathVariable Long securityId, BigDecimal spread) {
        priceService.updateSpread(securityId, spread);
    }
}
