package com.proyecto.core.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity (name = "product_price")
@NoArgsConstructor
public class ProductPrice {

    @EmbeddedId
    ProductPriceKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @MapsId("priceTypeId")
    @JoinColumn(name = "pricetype_id")
    PriceType priceType;

    Integer price = 0;

    public ProductPrice(ProductPriceKey id, Product product, PriceType priceType, Integer price) {
        this.id = id;
        this.product = product;
        this.priceType = priceType;
        this.price = price;
    }

    public ProductPriceKey getId() {
        return id;
    }

    public void setId(ProductPriceKey id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
