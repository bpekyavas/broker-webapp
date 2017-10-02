package com.mse.brokerwebapp.infrastructure.rest;

import com.mse.brokerwebapp.application.controller.PriceController;
import com.mse.brokerwebapp.application.converter.PriceVoListToResponseConverter;
import com.mse.brokerwebapp.application.converter.PriceVoToResponseConverter;
import com.mse.brokerwebapp.application.model.request.UpdateSpreadRequest;
import com.mse.brokerwebapp.application.model.response.PriceListResponse;
import com.mse.brokerwebapp.application.model.response.PriceResponse;
import com.mse.brokerwebapp.domain.service.PriceService;
import com.mse.brokerwebapp.domain.vo.PriceVo;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateSpread(@PathVariable Long securityId,@RequestBody UpdateSpreadRequest updateSpreadRequest) {
        priceService.updateSpread(securityId, updateSpreadRequest.getSpread());
    }
}
