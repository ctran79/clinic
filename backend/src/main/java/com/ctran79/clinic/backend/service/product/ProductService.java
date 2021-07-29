package com.ctran79.clinic.backend.service.product;

import com.ctran79.clinic.backend.domain.product.Product;
import com.ctran79.clinic.backend.domain.product.ProductDto;
import com.ctran79.clinic.backend.service.BaseCrudService;
import com.ctran79.clinic.backend.specification.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

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
