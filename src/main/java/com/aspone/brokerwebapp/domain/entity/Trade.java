package com.aspone.brokerwebapp.domain.entity;

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
    @JoinColumn(name="traderId", referencedColumnName = "id" , nullable = false)
    private Trader buyer;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="quantity")
    private Long quantity;

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

    public Trader getBuyer() {
        return buyer;
    }

    public void setBuyer(Trader buyer) {
        this.buyer = buyer;
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
}
