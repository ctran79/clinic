package com.ctran79.clinic.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ctran79
 */

@Controller
@RequestMapping("/print-controller")
public class PrintController {

    @GetMapping(path = "/order/{id}")
    public String printOrder(@PathVariable Long id) {
        return "print/order";
    }
}
