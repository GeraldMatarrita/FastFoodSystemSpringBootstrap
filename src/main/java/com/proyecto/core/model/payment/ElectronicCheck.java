package com.proyecto.core.model.payment;

public class ElectronicCheck extends AbstractPaymentType {

    private String checkNumber;
    private String count;

    public ElectronicCheck(String id, String checkNumber, String count) {
        super(id);
        this.checkNumber = checkNumber;
        this.count = count;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
