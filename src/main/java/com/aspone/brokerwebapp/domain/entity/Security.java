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

    @Column(name="symbol")
    private String symbol;

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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    public BigDecimal getBid(){
        return price.subtract(getSpread());
    }

    public BigDecimal getOffer(){
        return price.add(getSpread());
    }
}
