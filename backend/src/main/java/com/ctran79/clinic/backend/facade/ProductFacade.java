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

    @Override
    public ProductDto toDto(Product entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .note(entity.getNote())
                .build();
    }

    @Override
    public Product toEntity(ProductDto dto) {
        Product product = Optional.ofNullable(dto.getId())
                .map(productService::getById)
                .orElseGet(() -> new Product());

        return product.toEntity(dto);
    }
}
