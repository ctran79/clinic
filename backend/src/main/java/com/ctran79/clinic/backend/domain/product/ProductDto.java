package com.ctran79.clinic.backend.domain.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ctran79
 */

@Builder
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String code;
    private String name;
    private String note;
}
