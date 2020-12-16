package com.taeseok.apis.route;

import com.taeseok.apis.model.Product;
import com.taeseok.apis.service.ProductService;
import com.taeseok.apis.vo.ProductRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRoute {
    private final ProductService productService;

    @Autowired
    public ProductRoute(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{product_id}")
    @ResponseBody
    public Product getProduct(@PathVariable(value="product_id") String productId) throws  Exception{
        return this.productService.find(Integer.parseInt(productId));
    }

    @GetMapping("/initialize")
    public void initializeProducts(){
        this.productService.initializeProducts();
    }

    @DeleteMapping("/{product_id")
    public void deleteProduct(@PathVariable(value="product_id") String productId) throws Exception{
        this.productService.deleteProducts(Integer.parseInt(productId));
    }

    @PostMapping
    public void createProduct(ProductRegisterVO product){
        this.productService.createProduct(product);
    }
}

