package com.proyecto.core.model.payment;

public class Card extends AbstractPaymentType {

    private CardType type;
    private Integer number;
    private Integer expMonth;
    private Integer expYear;
    private Integer cvv;

    public Card(String id, CardType type, Integer number, Integer expMonth, Integer expYear, Integer cvv) {
        super(id);
        this.type = type;
        this.number = number;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cvv = cvv;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }
}
