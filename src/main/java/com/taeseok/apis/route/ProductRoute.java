package com.taeseok.apis.route;

import com.taeseok.apis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductRoute {
    private final ProductService productService;

    @Autowired
    public ProductRoute(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/initialize")
    public void initializeProducts(){
    this.productService.initializeProducts();
    }

}

