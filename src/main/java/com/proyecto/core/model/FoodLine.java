package com.proyecto.core.model;
import com.proyecto.core.model.payment.ProcessorPayment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "food_lines")
public class FoodLine implements Serializable {

    @Id
    private Integer id;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "foodLine")
    private List<Product> products;

    public FoodLine(){}

    public FoodLine(Integer id, String name) {
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
        return "FoodLine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}