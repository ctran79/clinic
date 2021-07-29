package com.ctran79.clinic.backend.service.product;

import com.ctran79.clinic.backend.domain.product.Product;
import com.ctran79.clinic.backend.service.BaseCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseCrudRepository<Product> {
}
