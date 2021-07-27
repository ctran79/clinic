package com.ctran79.clinic.backend.entity;

import com.ctran79.clinic.backend.domain.OrderDto;
import com.ctran79.clinic.backend.domain.OrderItemDto;
import com.ctran79.clinic.backend.specification.OrderSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ctran79
 */

public class OrderService extends BaseCrudService<Order, OrderDto> {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    protected Specification buildSpecification(Map<String, String> params) {
        return OrderSpecification.buildSpecification(params);
    }

    @Override
    public OrderDto toDto(Order entity) {
        OrderDto order = entity.toDto();

        order.setItems(prepareItems(entity.getItems()));
        return order;
    }

    private List<OrderItemDto> prepareItems(List<OrderItem> list) {
        return list.stream().map(OrderItem::toDto).collect(Collectors.toList());
    }

    @Override
    public Order toEntity(OrderDto dto) {
        Order order = Optional.ofNullable(dto.getId())
                .map(orderRepository::getById)
                .orElseGet(() -> new Order());

        order = order.toEntity(dto);
        order.getItems().clear();
        if (!dto.getItems().isEmpty()) {
            for (OrderItemDto itemDto : dto.getItems()) {
                OrderItem item = Optional.ofNullable(itemDto.getId())
                        .map(orderItemRepository::getById)
                        .orElseGet(() -> new OrderItem());
                item.toEntity(itemDto);
                order.addItem(item);
            }
        }
        return order;
    }
}
