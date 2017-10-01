package com.aspone.brokerwebapp.domain.service;

import com.aspone.brokerwebapp.application.model.request.RegisterRequest;
import com.aspone.brokerwebapp.domain.entity.ApplicationUser;
import com.aspone.brokerwebapp.domain.entity.Trader;
import com.aspone.brokerwebapp.domain.repository.ApplicationRoleRepository;
import com.aspone.brokerwebapp.domain.repository.ApplicationUserRepository;
import com.aspone.brokerwebapp.domain.repository.TraderRepository;
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
