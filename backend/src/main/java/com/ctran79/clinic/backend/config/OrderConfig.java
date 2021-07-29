package com.ctran79.clinic.backend.config;

import com.ctran79.clinic.backend.facade.OrderFacade;
import com.ctran79.clinic.backend.service.order.OrderItemRepository;
import com.ctran79.clinic.backend.service.order.OrderItemService;
import com.ctran79.clinic.backend.service.order.OrderRepository;
import com.ctran79.clinic.backend.service.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ctran79
 */

@Configuration
public class OrderConfig {

    @Bean
    public OrderFacade orderFacade(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        OrderService orderService = new OrderService(orderRepository, orderItemRepository);
        OrderItemService orderItemService = new OrderItemService(orderItemRepository);
        OrderFacade orderFacade = new OrderFacade(orderService, orderItemService);
        return orderFacade;
    }
}
