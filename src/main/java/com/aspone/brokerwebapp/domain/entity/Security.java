package com.aspone.brokerwebapp.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="security")
public class Security {

    @Id
    @Column(name="id")
    @GeneratedValue
    private Long id;

    @Column(name="code")
    private String code;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="spread")
    private BigDecimal spread;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }

    public BigDecimal getBestBid(){
        return price.subtract(getSpread());
    }

    public BigDecimal getBestAsk(){
        return price.add(getSpread());
    }
}
