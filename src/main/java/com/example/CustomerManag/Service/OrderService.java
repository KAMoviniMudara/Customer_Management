package com.example.CustomerManag.Service;

import com.example.CustomerManag.dto.requestDto.OrderRequestDto;
import com.example.CustomerManag.dto.responseDto.OrderResponseDto;
import com.example.CustomerManag.entity.Order;

import java.util.List;

public interface OrderService {
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto);
    public List<OrderResponseDto> getOrders();
    public OrderResponseDto getOrderById(Long orderId);
    public Order getOrder(Long orderId);
    public OrderResponseDto deleteOrder(Long orderId);
    public OrderResponseDto editOrder(Long orderId, OrderRequestDto orderRequestDto);
    public OrderResponseDto addCustomerToOrder(Long orderId, Long customerId);
    public OrderResponseDto deleteCustomerFromOrder(Long orderId);
}
