package com.aspone.brokerwebapp.domain.repository;

import com.aspone.brokerwebapp.domain.entity.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraderRepository extends JpaRepository<Trader,Long> {

}
