package com.ctran79.clinic.backend.service.order;

import com.ctran79.clinic.backend.domain.order.OrderItem;
import com.ctran79.clinic.backend.service.BaseCrudRepository;
import com.ctran79.clinic.backend.service.BaseCrudService;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

/**
 * @author ctran79
 */

public class OrderItemService extends BaseCrudService<OrderItem> {

    public OrderItemService(OrderItemRepository repository) {
        super(repository);
    }

    @Override
    protected Specification buildSpecification(Map<String, String> params) {
        throw new RuntimeException("Do not use this function");
    }
}
