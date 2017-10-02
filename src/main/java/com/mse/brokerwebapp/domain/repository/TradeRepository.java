package com.mse.brokerwebapp.domain.repository;

import com.mse.brokerwebapp.domain.entity.Trade;
import com.mse.brokerwebapp.domain.entity.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade,Long> {

    List<Trade> findAllByTrader(Trader trader);
}
