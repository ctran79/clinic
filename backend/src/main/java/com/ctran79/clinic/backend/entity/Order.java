package com.ctran79.clinic.backend.entity;

import com.ctran79.clinic.backend.domain.OrderDto;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
        setId(dto.getId());
        setClient(dto.getClient());
        setAddress(dto.getAddress());
        return this;
    }
}
