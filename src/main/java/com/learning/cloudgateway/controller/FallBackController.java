package com.learning.cloudgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("orderServiceFallBack")
    public String orderServiceFallBack() {
        return "Order service is not working";
    }

    @GetMapping("paymentServiceFallBack")
    public String paymentServiceFallBack() {
        return "Payment service is not working";
    }

    @GetMapping("productServiceFallBack")
    public String productServiceFallBack() {
        return "Product service is not working";
    }
}
