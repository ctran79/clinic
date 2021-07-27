package com.ctran79.clinic.backend.config;

import com.ctran79.clinic.backend.entity.*;
import com.ctran79.clinic.backend.facade.OrderFacade;
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
        return new OrderFacade(orderService);
    }
}
