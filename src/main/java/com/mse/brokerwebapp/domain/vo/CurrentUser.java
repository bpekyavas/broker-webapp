package com.mse.brokerwebapp.domain.vo;

import com.mse.brokerwebapp.domain.entity.Broker;
import com.mse.brokerwebapp.domain.entity.Trader;
import com.mse.brokerwebapp.domain.vo.enumtype.UserType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.stream.Collectors;

public class CurrentUser extends User{

    private Long id;
    private UserType userType;

    public CurrentUser(Trader trader) {
        super(trader.getApplicationUser().getUserName(), trader.getApplicationUser().getPassword(),
                trader.getApplicationUser().getApplicationRoles().stream()
                        .map(applicationRole -> new SimpleGrantedAuthority(applicationRole.getName())).collect(Collectors.toList()));
        this.id = trader.getId();
        this.userType = UserType.TRADER;
    }

    public CurrentUser(Broker broker) {
        super(broker.getApplicationUser().getUserName(), broker.getApplicationUser().getPassword(),
                broker.getApplicationUser().getApplicationRoles().stream()
                        .map(applicationRole -> new SimpleGrantedAuthority(applicationRole.getName())).collect(Collectors.toList()));
        this.id = broker.getId();
        this.userType = UserType.BROKER;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CurrentUser that = (CurrentUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return userType == that.userType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        return result;
    }
}
