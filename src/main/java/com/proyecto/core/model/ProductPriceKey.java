package com.proyecto.core.model;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class ProductPriceKey implements Serializable {
    @Column(name = "product_id")
    Integer productId;
    @Column(name = "pricetype_id")
    Integer priceTypeId;

    public ProductPriceKey(Integer productId, Integer priceTypeId) {
        this.productId = productId;
        this.priceTypeId = priceTypeId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPriceTypeId() {
        return priceTypeId;
    }

    public void setPriceTypeId(Integer priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPriceKey that = (ProductPriceKey) o;
        return Objects.equals(productId, that.productId) && Objects.equals(priceTypeId, that.priceTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, priceTypeId);
    }
}
