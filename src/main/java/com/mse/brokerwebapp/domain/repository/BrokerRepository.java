package com.mse.brokerwebapp.domain.repository;

import com.mse.brokerwebapp.domain.entity.ApplicationUser;
import com.mse.brokerwebapp.domain.entity.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerRepository extends JpaRepository<Broker,Long> {

    Broker findByApplicationUser(ApplicationUser applicationUser);
}
