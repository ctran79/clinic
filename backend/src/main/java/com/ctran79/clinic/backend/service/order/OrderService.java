package com.ctran79.clinic.backend.service.order;

import com.ctran79.clinic.backend.domain.order.Order;
import com.ctran79.clinic.backend.domain.order.OrderDto;
import com.ctran79.clinic.backend.service.BaseCrudService;
import com.ctran79.clinic.backend.specification.OrderSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ctran79
 */

public class OrderService extends BaseCrudService<Order> {
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
}
