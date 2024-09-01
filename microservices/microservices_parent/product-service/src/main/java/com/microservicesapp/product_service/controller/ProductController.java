package com.microservicesapp.product_service.controller;

import com.microservicesapp.product_service.dto.ProductRequest;
import com.microservicesapp.product_service.dto.ProductResponse;
import com.microservicesapp.product_service.model.Product;
import com.microservicesapp.product_service.service.ProductSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductSevice productSevice;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {

        productSevice.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productSevice.getAllProducts();
    }
}
