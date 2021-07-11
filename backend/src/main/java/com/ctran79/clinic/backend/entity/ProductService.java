package com.ctran79.clinic.backend.entity;

/**
 * @author ctran79
 */

public class ProductService extends BaseCrudService<Product> {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }
}
