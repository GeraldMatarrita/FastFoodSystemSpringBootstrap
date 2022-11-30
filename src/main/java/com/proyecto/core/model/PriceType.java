package com.proyecto.core.model;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "price_types")
@NoArgsConstructor
public class PriceType implements Serializable {

    @Id
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "priceType")
    Set<ProductPrice> prices;

    public PriceType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PriceType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
