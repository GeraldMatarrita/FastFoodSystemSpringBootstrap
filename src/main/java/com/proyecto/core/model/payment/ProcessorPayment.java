package com.proyecto.core.model.payment;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "payment_processors")
@NoArgsConstructor
public class ProcessorPayment {

    @Id
   private String id;
    @Column
   private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_type")
   private PaymentType type;
    @Column
   private Boolean state = false;
    @Column
   private Boolean verification = false;
    @Column
   private String method = null;
    @ManyToMany
    @JoinTable(name = "cards_processor", joinColumns = @JoinColumn(name = "processor_id"),
    inverseJoinColumns = @JoinColumn(name = "card_type_id"))
    private Set<CardType> cardTypes;

    public ProcessorPayment(String code, String processor, PaymentType type, Boolean state, Boolean verification, String method) {
        this.id = code;
        this.name = processor;
        this.type = type;
        this.state = state;
        this.verification = verification;
        this.method = method;
    }

    public String getId() {
        return id;
    }

    public void setId(String code) {
        this.id = code;
    }

    public String getName() {

        if (name == null) {
            return "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getVerification() {
        return verification;
    }

    public void setVerification(Boolean verification) {
        this.verification = verification;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "ProcessorPayment{" +
                "code=" + id +
                ", processor='" + name + '\'' +
//                ", type=" + type +
                ", state='" + state + '\'' +
                ", verification='" + verification + '\'' +
                ", method='" + method + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessorPayment that = (ProcessorPayment) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(state, that.state) && Objects.equals(verification, that.verification) && Objects.equals(method, that.method) && Objects.equals(cardTypes, that.cardTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, state, verification, method, cardTypes);
    }
}
