package com.aspone.brokerwebapp.domain.repository;

import com.aspone.brokerwebapp.domain.entity.ApplicationUser;
import com.aspone.brokerwebapp.domain.entity.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerRepository extends JpaRepository<Broker,Long> {

    Broker findByApplicationUser(ApplicationUser applicationUser);
}
