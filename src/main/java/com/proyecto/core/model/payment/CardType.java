package com.proyecto.core.model.payment;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "card_types")
public class CardType {
    @Column
    private String name;
    @Id
    private Integer id;

    @ManyToMany(mappedBy = "cardTypes")
    Set<ProcessorPayment> processors;

    CardType(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public CardType() {

    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
