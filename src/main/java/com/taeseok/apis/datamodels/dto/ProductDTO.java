package com.taeseok.apis.datamodels.dto;

import com.taeseok.apis.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProductDTO {
    private int productId;
    private String name;
    private String description;
    private int listPrice;
    private int price;
    private String category;
    private String imageURL;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.listPrice = product.getListPrice();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.imageURL = product.getImageURL();
    }

    @Override
    public String toString() {
        return String.format(
                "ProductDTO[productId='%s', name='%s', description='%s', listPrice=%d, price=%d, category='%s', imageURL='%s']",
                this.productId, this.name, this.description, this.listPrice, this.price, this.category, this.imageURL
        );
    }
}
