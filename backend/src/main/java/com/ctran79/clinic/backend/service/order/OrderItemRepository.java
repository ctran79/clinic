package com.ctran79.clinic.backend.service.order;

import com.ctran79.clinic.backend.domain.order.OrderItem;
import com.ctran79.clinic.backend.service.BaseCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends BaseCrudRepository<OrderItem> {
}
