package com.mse.brokerwebapp.domain.repository;

import com.mse.brokerwebapp.domain.entity.Security;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityRepository extends JpaRepository<Security,Long>{

    Optional<Security> findSecuritiesById(Long id);
}
