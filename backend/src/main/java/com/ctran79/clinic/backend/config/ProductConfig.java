package com.ctran79.clinic.backend.config;

import com.ctran79.clinic.backend.entity.ProductRepository;
import com.ctran79.clinic.backend.entity.ProductService;
import com.ctran79.clinic.backend.facade.ProductFacade;
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
        return new ProductFacade(productService);
    }
}
