package com.ecommerce.orderservice.service.impl;

import com.ecommerce.orderservice.constants.ExceptionMessage;
import com.ecommerce.orderservice.constants.OrderStatus;
import com.ecommerce.orderservice.dto.OrderDTO;
import com.ecommerce.orderservice.entity.OrderEntity;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.OrderService;
import com.ecommerce.orderservice.utils.communicator.InventoryCommunicator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final InventoryCommunicator inventoryCommunicator;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, InventoryCommunicator inventoryCommunicator) {
        this.orderRepository = orderRepository;
        this.inventoryCommunicator = inventoryCommunicator;
    }

    @Transactional
    @Override
    public boolean createOrder(OrderDTO orderDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseEntity<Boolean> inventoryUpdated = inventoryCommunicator.updateStockOnPurchaseOrCancel((String) SecurityContextHolder.getContext().getAuthentication().getCredentials(), orderDTO.getProductId(), orderDTO.getQuantity());
        if (!Boolean.TRUE.equals(inventoryUpdated.getBody())) {
            return false;
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setBuyerUsername(username);
        orderEntity.setProductId(orderDTO.getProductId());
        orderEntity.setQuantity(orderDTO.getQuantity());
        orderEntity.setStatus(OrderStatus.BOOKED.toString());
        orderRepository.save(orderEntity);
        return true;
    }

    @Transactional
    @Override
    public boolean cancelOrder(Long orderId) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderId);
        if (orderEntityOptional.isEmpty()) {
            throw new RuntimeException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
        OrderEntity orderEntity = orderEntityOptional.get();
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!orderEntity.getBuyerUsername().equals(loggedInUsername)) {
            throw new RuntimeException(ExceptionMessage.UNAUTHORIZED_ATTEMPT.getMessage());
        }
        ResponseEntity<Boolean> inventoryUpdated = inventoryCommunicator.updateStockOnPurchaseOrCancel((String) SecurityContextHolder.getContext().getAuthentication().getCredentials(),orderEntity.getProductId(), -1 * orderEntity.getQuantity());
        if (!Boolean.TRUE.equals(inventoryUpdated.getBody())) {
            return false;
        }
        orderEntity.setStatus(OrderStatus.CANCELED.toString());
        orderRepository.save(orderEntity);
        return true;
    }

    @Transactional
    @Override
    public List<OrderDTO> getAllOrders() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<OrderEntity> orderEntities = orderRepository.findByBuyerUsername(username);
        return orderEntities.stream().map(x -> {
           var orderDTO = new OrderDTO();
           orderDTO.setProductId(x.getProductId());
           orderDTO.setQuantity(x.getQuantity());
           orderDTO.setStatus(x.getStatus());
           return orderDTO;
        }).toList();
    }
}
