package com.aspone.brokerwebapp.domain.repository;

import com.aspone.brokerwebapp.domain.entity.Trade;
import com.aspone.brokerwebapp.domain.entity.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade,Long> {

    List<Trade> findAllByBuyer(Trader trader);
}
