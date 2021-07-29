package com.ctran79.clinic.backend.domain.order;

import com.ctran79.clinic.backend.domain.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author ctran79
 */

@Entity
@Getter
@Setter
@Table(name = "order_items")
public class OrderItem extends BaseEntity {
    @NotNull
    private Integer seqNo;
    @NotNull
    private Long productId;
    @NotNull
    private String productName;
    private String note;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItemDto toDto() {
        return OrderItemDto.builder()
                .id(getId())
                .seqNo(getSeqNo())
                .productId(getProductId())
                .productName(getProductName())
                .note(getNote())
                .build();
    }

    public void toEntity(OrderItemDto dto) {
        setSeqNo(dto.getSeqNo());
        setProductId(dto.getProductId());
        setProductName(dto.getProductName());
        setNote(dto.getNote());
    }
}
