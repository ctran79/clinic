package com.ctran79.clinic.backend.domain;

import lombok.Builder;
import lombok.Setter;

/**
 * @author ctran79
 */

@Builder
@Setter
public class ProductDto {
    private Long id;
    private String code;
    private String name;
}
