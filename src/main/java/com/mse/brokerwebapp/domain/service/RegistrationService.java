package com.mse.brokerwebapp.domain.service;

import com.mse.brokerwebapp.application.model.request.RegisterRequest;
import com.mse.brokerwebapp.domain.entity.ApplicationUser;
import com.mse.brokerwebapp.domain.entity.Trader;
import com.mse.brokerwebapp.domain.repository.ApplicationRoleRepository;
import com.mse.brokerwebapp.domain.repository.ApplicationUserRepository;
import com.mse.brokerwebapp.domain.repository.TraderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationService {

    private TraderRepository traderRepository;

    public RegistrationService(TraderRepository traderRepository, ApplicationRoleRepository applicationRoleRepository, ApplicationUserRepository applicationUserRepository) {
        this.traderRepository = traderRepository;
    }

    public void register(RegisterRequest registerRequest) {
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUserName(registerRequest.getUserName());
        applicationUser.setPassword(registerRequest.getPassword());
        Trader trader = new Trader();
        trader.setName(registerRequest.getName());
        trader.setApplicationUser(applicationUser);

        traderRepository.save(trader);
    }
}
