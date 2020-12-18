package com.taeseok.apis.service;

import com.taeseok.apis.model.Product;
import com.taeseok.apis.model.Review;
import com.taeseok.apis.repository.ProductRepository;
import com.taeseok.apis.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    public void initializeReviews() {
        Product product1 = this.productRepository.getOne(1);

        Review review1 = Review.builder()
                .userId(1)
                .rate(5)
                .review("너무 좋아요! 잘쓰고 있습니다")
                .build();

        Review review2 = Review.builder()
                .userId(2)
                .rate(3)
                .review("그냥 그렇습니다... 애매해요")
                .build();


        this.reviewRepository.save(review1);
        this.reviewRepository.save(review2);

        product1.getReview().add(review1);
        product1.getReview().add(review2);

        this.reviewRepository.flush();
    }
}
