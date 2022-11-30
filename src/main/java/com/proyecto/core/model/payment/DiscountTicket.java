package com.proyecto.core.model.payment;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "discount_tickets")
public class DiscountTicket {

    @Id
    private String id;
    @Column
    private String name;
    @Column
    private Integer stock;
    @Column
    private Integer percent;
    public DiscountTicket() {
    }

    public DiscountTicket(String id, String name, Integer stock, Integer percent) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.percent = percent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }
}
