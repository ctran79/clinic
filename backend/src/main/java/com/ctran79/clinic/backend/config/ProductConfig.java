package com.ctran79.clinic.backend.config;

import com.ctran79.clinic.backend.facade.ProductFacade;
import com.ctran79.clinic.backend.service.product.ProductRepository;
import com.ctran79.clinic.backend.service.product.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ctran79
 */

@Configuration
public class ProductConfig {

    @Bean
    public ProductFacade productFacade(ProductRepository productRepository) {
        ProductService productService = new ProductService(productRepository);
        ProductFacade productFacade = new ProductFacade(productService);
        return productFacade;
    }
}
