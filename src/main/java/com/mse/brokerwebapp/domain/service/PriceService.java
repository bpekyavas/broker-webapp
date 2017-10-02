package com.mse.brokerwebapp.domain.service;

import com.mse.brokerwebapp.application.converter.SecurityToPriceVoConverter;
import com.mse.brokerwebapp.domain.entity.Security;
import com.mse.brokerwebapp.domain.exception.PriceUpdateBusinessException;
import com.mse.brokerwebapp.domain.exception.SecurityNotFoundBusinessException;
import com.mse.brokerwebapp.domain.repository.SecurityRepository;
import com.mse.brokerwebapp.domain.vo.PriceVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PriceService {

    private SecurityToPriceVoConverter securityToPriceVoConverter;
    private SecurityRepository securityRepository;

    public PriceService(SecurityToPriceVoConverter securityToPriceVoConverter, SecurityRepository securityRepository) {
        this.securityToPriceVoConverter = securityToPriceVoConverter;
        this.securityRepository = securityRepository;
    }

    public PriceVo retrievePriceInfo(Long securityId) {
        Security security = Optional.ofNullable(securityRepository.findOne(securityId))
                .orElseThrow(() -> new SecurityNotFoundBusinessException("Security is not found!"));
        return securityToPriceVoConverter.convert(security);
    }

    public List<PriceVo> retrievePriceList() {
        List<Security> securityList = securityRepository.findAll();
        if (securityList.isEmpty()) {
            throw new SecurityNotFoundBusinessException("No securities found!");
        }
        return securityList.stream().map(security ->
                securityToPriceVoConverter.convert(security)).collect(Collectors.toList());
    }

    public void updateSpread(Long securityId, BigDecimal spread) {
        Security security = Optional.ofNullable(securityRepository.findOne(securityId))
                .orElseThrow(() -> new SecurityNotFoundBusinessException("Security is not found!"));
        security.setSpread(spread);
        try {
            securityRepository.save(security);
        } catch (Exception e) {
            throw new PriceUpdateBusinessException("Price could not be updated");
        }
    }
}
