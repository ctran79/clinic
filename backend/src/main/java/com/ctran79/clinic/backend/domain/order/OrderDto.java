package com.ctran79.clinic.backend.domain.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ctran79
 */

@Builder
@Getter
@Setter
public class OrderDto {
    private Long id;
    private String client;
    private String address;
    private LocalDateTime createDate;

    private List<OrderItemDto> items;
}
