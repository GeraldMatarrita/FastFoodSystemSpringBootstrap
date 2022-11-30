package com.proyecto.core.model;

public class ProductPriceType {

    private Integer productID;

    private Integer priceTypeID;

    private Integer price = 0;

    public ProductPriceType() {
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getPriceTypeID() {
        return priceTypeID;
    }

    public void setPriceTypeID(Integer priceTypeID) {
        this.priceTypeID = priceTypeID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
