package com.taeseok.apis.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(length = 80, nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private int listPrice;

    @Column
    private int price;

    @Column(length = 40)
    private String category;

    @OneToMany
    @JoinColumn(name="product_id")
    private Collection<Review> review = new ArrayList<>;

    @Column
    private String imgUrl;

    @Builder
    public Product(String name, String description, int listPrice, int Price, String category, String imgUrl) {
        this.name = name;
        this.description = description;
        this.listPrice = listPrice;
        this.price = Price;
        this.category = category;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[productId='%s', name='%s', description='%s', listPrice=%d, price=%d, category='%s', imgUrl='%s']",
                this.productId, this.name, this.description, this.listPrice, this.price, this.category, this.imgUrl
        );
    }
}
