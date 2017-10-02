package com.mse.brokerwebapp.domain.service;

import com.mse.brokerwebapp.domain.entity.ApplicationUser;
import com.mse.brokerwebapp.domain.entity.Broker;
import com.mse.brokerwebapp.domain.entity.Trader;
import com.mse.brokerwebapp.domain.repository.ApplicationRoleRepository;
import com.mse.brokerwebapp.domain.repository.ApplicationUserRepository;
import com.mse.brokerwebapp.domain.repository.BrokerRepository;
import com.mse.brokerwebapp.domain.repository.TraderRepository;
import com.mse.brokerwebapp.domain.vo.CurrentUser;
import com.google.common.collect.Lists;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private ApplicationUserRepository applicationUserRepository;
    private TraderRepository traderRepository;
    private BrokerRepository brokerRepository;
    private ApplicationRoleRepository applicationRoleRepository;

    public ApplicationUserDetailsService(ApplicationUserRepository applicationUserRepository, TraderRepository traderRepository, BrokerRepository brokerRepository, ApplicationRoleRepository applicationRoleRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.traderRepository = traderRepository;
        this.brokerRepository = brokerRepository;
        this.applicationRoleRepository = applicationRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUserName(s);
        if (applicationUser != null) {
            Trader trader = traderRepository.findByApplicationUser(applicationUser);
            if (trader != null) {
                trader.getApplicationUser().setApplicationRoles(Lists.newArrayList(applicationRoleRepository.findByName("ROLE_USER")));
                return new CurrentUser(trader);
            }
            Broker broker = brokerRepository.findByApplicationUser(applicationUser);
            if (broker != null) {
                broker.getApplicationUser().setApplicationRoles(Lists.newArrayList(applicationRoleRepository.findByName("ROLE_ADMIN")));
                return new CurrentUser(broker);
            }
        }
        throw new UsernameNotFoundException("User name could not found!");
    }
}
