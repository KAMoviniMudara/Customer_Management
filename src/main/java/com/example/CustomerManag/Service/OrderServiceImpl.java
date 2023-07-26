package com.example.CustomerManag.Service;

import com.example.CustomerManag.dto.mapper;
import com.example.CustomerManag.dto.requestDto.OrderRequestDto;
import com.example.CustomerManag.dto.responseDto.OrderResponseDto;
import com.example.CustomerManag.entity.Customer;
import com.example.CustomerManag.entity.Order;
import com.example.CustomerManag.repository.CustomerRepository;
import com.example.CustomerManag.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setId(orderRequestDto.getId());
        if (orderRequestDto.getCustomerId() == null) {
            throw new IllegalArgumentException("order need a customer");
        }
        Customer customer = customerService.getCustomer(orderRequestDto.getCustomerId());
        customer.setCustomer(customer);
        orderRepository.save(order);
        return mapper.orderToOrderResponseDto(order); order = new Order();
        order.setName(orderRequestDto.getName());
        if (orderRequestDto.getZipcodeId() == null) {
            throw new IllegalArgumentException("order need a zipcode");
        }
        Customer customer = customerService.getCustomer(customerRequestDto.getCustomerId());
        customer.setCustomer(customer);
        orderRepository.save(order);
        return mapper.orderToOrderResponseDto(order);
    }

    @Override
    public List<OrderResponseDto> getOrders() {
        List<Order> orders = StreamSupport
                .stream(orderRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.ordersToOrderResponseDtos(orders);
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        return null;
    }

    @Override
    public Order getOrder(Long orderId) {
        return null;
    }

    @Override
    public OrderResponseDto deleteOrder(Long orderId) {
        return null;
    }

    @Override
    public OrderResponseDto editOrder(Long orderId, OrderRequestDto orderRequestDto) {
        return null;
    }

    @Override
    public OrderResponseDto addCustomerToOrder(Long orderId, Long customerId) {
        return null;
    }

    @Override
    public OrderResponseDto deleteCustomerFromOrder(Long orderId) {
        return null;
    }
}
