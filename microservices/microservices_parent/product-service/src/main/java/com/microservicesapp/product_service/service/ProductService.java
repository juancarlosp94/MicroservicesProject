package com.microservicesapp.product_service.service;

import com.microservicesapp.product_service.dto.ProductRequest;
import com.microservicesapp.product_service.dto.ProductResponse;
import com.microservicesapp.product_service.model.Product;
import com.microservicesapp.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId()); //Be aware: product.id or product.id(productRequest.id())
        // or Product.builder().id(productRequest.id())

        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());

    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .toList();

        //List<Product> products = productRepository.findAll();

        // Map each product into a ProductResponse object
        //return products.stream().map(this :: mapToProductResponse).toList();
    }

//    private ProductResponse mapToProductResponse(Product product) {
//        return ProductResponse.builder()
//                .id(product.getId())
//                .name(product.getName())
//                .description(product.getDescription())
//                .price(product.getPrice())
//                .build();
//    }

    // CODE COMMENTS INCLUDE THE PREVIOUS VERSION. IT HAS NOT BEEN ERASED FOR ACADEMIC PURPOSES.
}
