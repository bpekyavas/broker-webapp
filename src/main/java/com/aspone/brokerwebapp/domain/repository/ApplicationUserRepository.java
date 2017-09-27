package com.aspone.brokerwebapp.domain.repository;

import com.aspone.brokerwebapp.domain.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {
    ApplicationUser findByUserName(String s);
}
