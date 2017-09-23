package com.aspone.brokerwebapp.infrastructure.rest;

import com.aspone.brokerwebapp.application.controller.PriceController;
import com.aspone.brokerwebapp.application.converter.PriceVoListToResponseConverter;
import com.aspone.brokerwebapp.application.converter.PriceVoToResponseConverter;
import com.aspone.brokerwebapp.application.model.response.PriceListResponse;
import com.aspone.brokerwebapp.application.model.response.PriceResponse;
import com.aspone.brokerwebapp.domain.service.PriceService;
import com.aspone.brokerwebapp.domain.vo.PriceVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class RestPriceController implements PriceController {

    private PriceService priceService;
    private PriceVoToResponseConverter priceVoToResponseConverter;
    private PriceVoListToResponseConverter priceVoListToResponseConverter;

    public RestPriceController(PriceService priceService, PriceVoToResponseConverter priceVoToResponseConverter, PriceVoListToResponseConverter priceVoListToResponseConverter) {
        this.priceService = priceService;
        this.priceVoToResponseConverter = priceVoToResponseConverter;
        this.priceVoListToResponseConverter = priceVoListToResponseConverter;
    }

    @Override
    @GetMapping("/api/v1/prices/{securityId}")
    @ResponseStatus(HttpStatus.OK)
    public PriceResponse retrievePrice(@PathVariable Long securityId) {
        PriceVo priceVo = priceService.retrievePriceInfo(securityId);
        return priceVoToResponseConverter.convert(priceVo);
    }

    @Override
    @GetMapping("/api/v1/prices")
    @ResponseStatus(HttpStatus.OK)
    public PriceListResponse retrieveAllPrices() {
        List<PriceVo> priceVoList = priceService.retrievePriceList();
        return priceVoListToResponseConverter.convert(priceVoList);
    }

    @Override
    @PostMapping("/api/v1/prices/{securityId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateSpread(@PathVariable Long securityId, BigDecimal spread) {
        priceService.updateSpread(securityId, spread);
    }
}
