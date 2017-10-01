package com.aspone.brokerwebapp.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="application_user")
public class ApplicationUser {
    @Id
    @Column(name="id")
    @GeneratedValue
    private Long id;

    @Column(name="user_name", nullable = false)
    private String userName;

    @Column(name="password", nullable = false)
    private String password;

    @ManyToMany
    private List<ApplicationRole> applicationRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ApplicationRole> getApplicationRoles() {
        return applicationRoles;
    }

    public void setApplicationRoles(List<ApplicationRole> applicationRoles) {
        this.applicationRoles = applicationRoles;
    }
}
