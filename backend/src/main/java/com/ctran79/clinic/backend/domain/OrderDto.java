package com.ctran79.clinic.backend.domain;

import com.ctran79.clinic.backend.entity.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
