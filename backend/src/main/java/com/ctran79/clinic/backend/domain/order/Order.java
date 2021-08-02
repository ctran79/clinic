package com.ctran79.clinic.backend.domain.order;

import com.ctran79.clinic.backend.domain.BaseEntity;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ctran79
 */
@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseEntity {
    @NotNull
    private String client;
    private String address;
    private LocalDateTime createDate;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }

    public void addItem(OrderItem item) {
        item.setOrder(this);
        items.add(item);
    }

    public OrderDto toDto() {
        return OrderDto.builder()
                .id(getId())
                .client(getClient())
                .address(getAddress())
                .createDate(getCreateDate())
                .build();
    }

    public Order toEntity(OrderDto dto) {
        setClient(dto.getClient());
        setAddress(dto.getAddress());
        return this;
    }
}