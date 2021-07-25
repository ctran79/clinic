package com.ctran79.clinic.backend.entity;

import com.ctran79.clinic.backend.specification.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/**
 * @author ctran79
 */

public class ProductService extends BaseCrudService<Product> {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    @Override
    protected Specification buildSpecification(Map<String, String> params) {
        return ProductSpecification.buildSpecification(params);
    }
}
