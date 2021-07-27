package com.ctran79.clinic.backend.facade;

import com.ctran79.clinic.backend.domain.OrderDto;
import com.ctran79.clinic.backend.domain.OrderItemDto;
import com.ctran79.clinic.backend.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ctran79
 */
@Service
public class OrderFacade extends BaseCrudFacade<Order, OrderDto> {
    private final OrderService orderService;

    public OrderFacade(OrderService orderService) {
        super(orderService);
        this.orderService = orderService;
    }
}