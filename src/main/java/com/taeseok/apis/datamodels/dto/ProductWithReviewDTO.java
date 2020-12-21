package com.taeseok.apis.datamodels.dto;

import com.taeseok.apis.model.IssuedCoupon;
import com.taeseok.apis.model.Product;
import com.taeseok.apis.model.Review;

import java.util.Collection;

public class ProductWithReviewDTO {
    private int productId;
    private String name;
    private String description;
    private int listPrice;
    private int price;
    private String category;
    private String imageURL;
    private Collection<Review> review;

    public ProductWithReviewDTO(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.listPrice = product.getListPrice();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.imageURL = product.getImageURL();
        this.review = product.getReview();
    }

    @Override
    public String toString() {
        return String.format(
                "ProductDTO[productId='%s', name='%s', description='%s', listPrice=%d, price=%d, category='%s', imageURL='%s']",
                this.productId, this.name, this.description, this.listPrice, this.price, this.category, this.imageURL
        );
    }

}
