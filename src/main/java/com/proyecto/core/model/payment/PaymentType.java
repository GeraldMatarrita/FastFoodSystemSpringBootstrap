package com.proyecto.core.model.payment;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "payment_types")
@NoArgsConstructor
public class PaymentType {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column
    private String name;
    @OneToMany(mappedBy = "type")
    private List<ProcessorPayment> processors;

    public PaymentType(Integer id) {
        this.id = id;
        this.processors = new ArrayList<>();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentType that = (PaymentType) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(processors, that.processors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, processors);
    }

    @Override
    public String toString() {
        return "PaymentType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", processors=" + processors +
                '}';
    }
}
