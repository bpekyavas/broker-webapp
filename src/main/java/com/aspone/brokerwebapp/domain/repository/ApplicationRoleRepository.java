package com.aspone.brokerwebapp.domain.repository;

import com.aspone.brokerwebapp.domain.entity.ApplicationRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRoleRepository extends JpaRepository<ApplicationRole,Long> {

    ApplicationRole findByName(String name);
}
