package com.ctran79.clinic.backend.domain.order;

import lombok.Builder;
import lombok.Getter;

/**
 * @author ctran79
 */

@Builder
@Getter
public class OrderItemDto {
    private Long id;
    private Integer seqNo;
    private Long productId;
    private String productName;
    private String note;
}
