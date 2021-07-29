package com.ctran79.clinic.backend.controller;

import com.ctran79.clinic.backend.domain.product.ProductDto;
import com.ctran79.clinic.backend.domain.product.Product;
import com.ctran79.clinic.backend.facade.ProductFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ctran79
 */

@RestController
@RequestMapping(value = "/product")
public class ProductController extends BaseCrudController<Product, ProductDto>{

    public ProductController(ProductFacade productFacade) {
        super(productFacade);
    }
}
