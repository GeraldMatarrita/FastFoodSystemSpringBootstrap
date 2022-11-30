package com.proyecto.core.model;
import com.proyecto.core.model.payment.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
public class Product implements Serializable{

    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    @Lob
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodline")
    private FoodLine foodLine;
    @Column
    @Lob
    private String image;
    @OneToMany(mappedBy = "product")
    Set<ProductPrice> prices;

    public Product(Integer id, String name, String description, String content, String image) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.image = image;
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", image=" + image +
                '}';
    }
}

