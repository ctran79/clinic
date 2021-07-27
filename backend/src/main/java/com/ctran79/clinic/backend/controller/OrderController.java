package com.ctran79.clinic.backend.controller;

import com.ctran79.clinic.backend.domain.OrderDto;
import com.ctran79.clinic.backend.domain.ProductDto;
import com.ctran79.clinic.backend.entity.Order;
import com.ctran79.clinic.backend.entity.Product;
import com.ctran79.clinic.backend.facade.OrderFacade;
import com.ctran79.clinic.backend.facade.ProductFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ctran79
 */

@RestController
@RequestMapping(value = "/order")
public class OrderController extends BaseCrudController<Order, OrderDto>{

    public OrderController(OrderFacade orderFacade) {
        super(orderFacade);
    }
}
