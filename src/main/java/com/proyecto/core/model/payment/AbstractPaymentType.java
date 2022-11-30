package com.proyecto.core.model.payment;


public abstract class AbstractPaymentType {
    private String id;
    private String name;

    public AbstractPaymentType(String id) {
        this.id = id;
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
}
