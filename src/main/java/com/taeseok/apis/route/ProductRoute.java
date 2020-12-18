package com.taeseok.apis.route;

import com.taeseok.apis.model.Product;
import com.taeseok.apis.service.ProductService;
import com.taeseok.apis.vo.ProductRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRoute {
    private final ProductService productService;

    @Autowired
    public ProductRoute(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{product_id}")
    @ResponseBody
    public Product getProduct(@PathVariable(value="product_id") String productId) throws Exception {
        return this.productService.find(Integer.parseInt(productId));
    }
    @GetMapping
    @ResponseBody
    public List<Product> getProducts(){
        return this.productService.findAll();
    }

    @GetMapping
    @ResponseBody
    public List<Product> getProducts() {
        return this.productService.findAll();
    }

    @GetMapping("/initialize")
    public void initializeProducts() {
        this.productService.initializeProducts();
    }

    @DeleteMapping("/{product_id}")
    public void deleteProduct(@PathVariable(value="product_id") String productId) throws Exception {
        this.productService.deleteProduct(Integer.parseInt(productId));
    }

    @PostMapping
    public int createProduct(ProductRegisterVO productRegisterVO) {
        return this.productService.createProduct(productRegisterVO);
    }

    @GetMapping("/category/{category_name}")
    @ResponseBody
    public List<Product> getProductsByCategory(@PathVariable(value = "category_name") String category_name){
        return this.productService.productsByCategory(category_name);
    }
}

