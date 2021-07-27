package com.ctran79.clinic.backend.domain;

import com.ctran79.clinic.backend.entity.BaseEntity;
import com.ctran79.clinic.backend.entity.Order;
import com.ctran79.clinic.backend.entity.OrderItem;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
