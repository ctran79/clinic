package com.ctran79.clinic.backend.facade;

import com.ctran79.clinic.backend.domain.order.Order;
import com.ctran79.clinic.backend.domain.order.OrderDto;
import com.ctran79.clinic.backend.domain.order.OrderItem;
import com.ctran79.clinic.backend.domain.order.OrderItemDto;
import com.ctran79.clinic.backend.service.order.OrderItemService;
import com.ctran79.clinic.backend.service.order.OrderService;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ctran79
 */

public class OrderFacade extends BaseCrudFacade<Order, OrderDto> {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    public OrderFacade(OrderService orderService, OrderItemService orderItemService) {
        super(orderService);
        this.orderService = orderService;
        this.orderItemService = orderItemService;
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
                .map(orderService::getById)
                .orElseGet(() -> new Order());
        order.toEntity(dto);

        order.getItems().clear();
        if (!CollectionUtils.isEmpty(dto.getItems())) {
            for (OrderItemDto itemDto : dto.getItems()) {
                OrderItem item = Optional.ofNullable(itemDto.getId())
                        .map(orderItemService::getById)
                        .orElseGet(() -> new OrderItem());
                item.toEntity(itemDto);
                order.addItem(item);
            }
        }
        return order;
    }
}