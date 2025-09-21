package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    boolean createOrder(OrderDTO orderDTO);

    boolean cancelOrder(Long orderId);

    List<OrderDTO> getAllOrders();




}
