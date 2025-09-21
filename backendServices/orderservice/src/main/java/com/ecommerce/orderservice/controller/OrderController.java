package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.OrderDTO;
import com.ecommerce.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyer/order")
public class OrderController {

    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> createNewOrder(@RequestBody OrderDTO orderDTO) {
        var isOrderCreated = orderService.createOrder(orderDTO);
        if (isOrderCreated) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<Boolean> cancelOrder(@PathVariable Long orderId) {
        var isOrderCanceled =  orderService.cancelOrder(orderId);
        if (isOrderCanceled) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        var allOrders = orderService.getAllOrders();
        return ResponseEntity.ok().body(allOrders);
    }


}
