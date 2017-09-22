package com.aspone.brokerwebapp.domain.service;

import com.aspone.brokerwebapp.application.converter.SecurityToPriceVoConverter;
import com.aspone.brokerwebapp.domain.entity.Security;
import com.aspone.brokerwebapp.domain.exception.PriceUpdateBusinessException;
import com.aspone.brokerwebapp.domain.exception.SecurityNotFoundBusinessException;
import com.aspone.brokerwebapp.domain.repository.SecurityRepository;
import com.aspone.brokerwebapp.domain.vo.PriceVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

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

    public void updatePrice(Long securityId, BigDecimal price) {
        Security security = Optional.ofNullable(securityRepository.findOne(securityId))
                .orElseThrow(() -> new SecurityNotFoundBusinessException("Security is not found!"));
        try {
            securityRepository.save(security);
        } catch (Exception e) {
            throw new PriceUpdateBusinessException("Price could not be updated");
        }
    }
}
