package com.mse.brokerwebapp.domain.repository;

import com.mse.brokerwebapp.domain.entity.ApplicationUser;
import com.mse.brokerwebapp.domain.entity.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraderRepository extends JpaRepository<Trader,Long> {

    Trader findByApplicationUser(ApplicationUser applicationUser);

}
