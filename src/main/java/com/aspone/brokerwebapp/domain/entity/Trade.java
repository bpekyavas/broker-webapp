package com.aspone.brokerwebapp.domain.entity;

import com.aspone.brokerwebapp.domain.entity.converter.SideConverter;
import com.aspone.brokerwebapp.domain.entity.enumtype.Side;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Trade {

    @Id
    @Column(name="id")
    @GeneratedValue
    private Long id;

    @Column(name="date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="securityId", referencedColumnName = "id" , nullable = false)
    private Security security;

    @ManyToOne
    @JoinColumn(name="traderId", referencedColumnName = "id" , nullable = false)
    private Trader trader;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="quantity")
    private Long quantity;

    @Column(name="side")
    @Convert(converter = SideConverter.class)
    private Side side;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }
}
