package com.taeseok.apis.route;

import com.taeseok.apis.service.ProductService;
import com.taeseok.apis.service.ReviewService;
import com.taeseok.apis.service.SaleService;
import com.taeseok.apis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class InitializeRoute {
    private final ProductService productService;
    private final ReviewService reviewService;
    private final SaleService saleService;
    private final UserService userService;

    @Autowired
    public InitializeRoute(ProductService productService,
                           ReviewService reviewService,
                           SaleService saleService,
                           UserService userService) {
        this.productService = productService;
        this.reviewService = reviewService;
        this.saleService = saleService;
        this.userService = userService;
    }

    @GetMapping("/initialize")
    public void initialize() {
        this.productService.initializeProducts();
        this.userService.initializeUsers();
        this.reviewService.initializeReviews();
        this.saleService.initializeSales();
    }
}