package com.taeseok.apis.route;

import com.taeseok.apis.datamodels.dto.ProductDTO;
import com.taeseok.apis.datamodels.vo.ProductRegisterVO;
import com.taeseok.apis.model.Product;
import com.taeseok.apis.service.ProductService;
import com.taeseok.apis.service.ReviewService;
import com.taeseok.apis.service.SaleService;
import com.taeseok.apis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/initialize")
public class InitializeRoute {
    private final UserService userService;
    private final ProductService productService;
    private final SaleService saleService;
    private final ReviewService reviewService;

    @Autowired
    public InitializeRoute(UserService userService,
                           ProductService productService,
                           SaleService saleService,
                           ReviewService reviewService) {
        this.userService = userService;
        this.productService = productService;
        this.saleService = saleService;
        this.reviewService = reviewService;
    }

    @GetMapping("")
    public void initialize() {
        this.userService.initializeUsers();
        this.productService.initializeProducts();
        this.saleService.initializeSales();
        this.reviewService.initializeReviews();
    }
}