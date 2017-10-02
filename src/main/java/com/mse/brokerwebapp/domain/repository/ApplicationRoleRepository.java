package com.mse.brokerwebapp.domain.repository;

import com.mse.brokerwebapp.domain.entity.ApplicationRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRoleRepository extends JpaRepository<ApplicationRole,Long> {

    ApplicationRole findByName(String name);
}
