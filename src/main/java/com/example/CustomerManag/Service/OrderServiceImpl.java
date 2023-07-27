package com.example.CustomerManag.Service;

import com.example.CustomerManag.dto.mapper;
import com.example.CustomerManag.dto.requestDto.OrderRequestDto;
import com.example.CustomerManag.dto.responseDto.OrderResponseDto;
import com.example.CustomerManag.entity.Customer;
import com.example.CustomerManag.entity.Order;
import com.example.CustomerManag.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
    }

    @Transactional
    @Override
    public OrderResponseDto addOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setId(Long.valueOf(orderRequestDto.getName()));
        if (orderRequestDto.getCustomerId() == null) {
            throw new IllegalArgumentException("order need a customer");
        }
        Customer customer = customerService.getCustomer(orderRequestDto.getCustomerId());
        order.setCustomer(customer);
        orderRepository.save(order);
        return mapper.orderToOrderResponseDto(order);
    }

    @Override
    public List<OrderResponseDto> getOrders() {
        List<Order> orders = StreamSupport
                .stream(orderRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.ordersToOrderResponseDto(orders);
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        return mapper.orderToOrderResponseDto(getOrder(orderId));
    }

    @Override
    public Order getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->
                new IllegalArgumentException(
                        "order with id: " + orderId + "could not be found"));
                return order;
    }

    @Override
    public OrderResponseDto deleteOrder(Long orderId) {
        Order order = getOrder(orderId);
        orderRepository.delete(order);
        return mapper.orderToOrderResponseDto(order);
    }

    @Override
    public OrderResponseDto editOrder(Long orderId, OrderRequestDto orderRequestDto) {
        Order orderToEdit = getOrder(orderId);
        orderToEdit.setId(orderRequestDto.getCustomerId());
        if (orderRequestDto.getCustomerId() != null){
            Customer customer = customerService.getCustomer(orderRequestDto.getCustomerId());
            orderToEdit.setCustomer(customer);
        }
        return mapper.orderToOrderResponseDto(orderToEdit);
    }

    @Override
    public OrderResponseDto addCustomerToOrder(Long orderId, Long customerId) {
        Order order = getOrder(orderId);
        Customer customer = customerService.getCustomer(customerId);
        if(Objects.nonNull(order.getCustomer())){
            throw new RuntimeException("Order already has a customer");
        }
        order.setCustomer(customer);
        return mapper.orderToOrderResponseDto(order);
    }

    @Override
    public OrderResponseDto deleteCustomerFromOrder(Long orderId) {
        Order order = getOrder(orderId);
        order.setCustomer(null);
        return mapper.orderToOrderResponseDto(order);
    }

    @Override
    public List<OrderResponseDto> getOrder() {
        return null;
    }
}
