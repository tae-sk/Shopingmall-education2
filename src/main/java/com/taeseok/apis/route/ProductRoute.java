package com.taeseok.apis.route;

import com.taeseok.apis.datamodels.dto.ProductDTO;
import com.taeseok.apis.model.Product;
import com.taeseok.apis.service.ProductService;
import com.taeseok.apis.datamodels.vo.ProductRegisterVO;
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

    @GetMapping("")
    @ResponseBody
    public List<ProductDTO> getProducts() { return this.productService.products(); }

    @GetMapping("/{product_id}")
    @ResponseBody
    public ProductDTO getProduct(@PathVariable(value="product_id") String productId) throws Exception {
        return this.productService.productById(Integer.parseInt(productId));
    }

    @PostMapping("")
    public int createProduct(ProductRegisterVO product) {
        return this.productService.createProduct(product);
    }

    @GetMapping("/initialize")
    public void initializeProducts() {
        this.productService.initializeProducts();
    }

    @DeleteMapping("/{product_id}")
    public void deleteProduct(@PathVariable(value = "product_id") String productId) {
        this.productService.deleteProduct(Integer.parseInt(productId));
    }

    @GetMapping("/category/{category_name}")
    @ResponseBody
    public List<ProductDTO> getProductsByCategory(@PathVariable(value = "category_name") String category_name) {
        return this.productService.productsByCategory(category_name);
    }
}

