package com.ctran79.clinic.backend.facade;

import com.ctran79.clinic.backend.domain.ProductDto;
import com.ctran79.clinic.backend.entity.Product;
import com.ctran79.clinic.backend.entity.ProductService;

import java.util.Optional;

/**
 * @author ctran79
 */

public class ProductFacade extends BaseCrudFacade<Product, ProductDto> {
    private final ProductService productService;

    public ProductFacade(ProductService productService) {
        super(productService);
        this.productService = productService;
    }
}
