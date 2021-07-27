package com.ctran79.clinic.backend.entity;

import com.ctran79.clinic.backend.domain.ProductDto;
import com.ctran79.clinic.backend.specification.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * @author ctran79
 */
@Service
public class ProductService extends BaseCrudService<Product, ProductDto> {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    @Override
    protected Specification buildSpecification(Map<String, String> params) {
        return ProductSpecification.buildSpecification(params);
    }

    @Override
    public ProductDto toDto(Product entity) {
        return entity.toDto();
    }

    @Override
    public Product toEntity(ProductDto dto) {
        Product product = Optional.ofNullable(dto.getId())
                .map(productRepository::getById)
                .orElseGet(() -> new Product());

        return product.toEntity(dto);
    }
}
