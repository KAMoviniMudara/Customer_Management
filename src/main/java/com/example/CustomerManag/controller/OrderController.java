package com.example.CustomerManag.controller;

import com.example.CustomerManag.Service.OrderService;
import com.example.CustomerManag.dto.requestDto.OrderRequestDto;
import com.example.CustomerManag.dto.responseDto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/addOrder")
    public ResponseEntity<OrderResponseDto> addOrder(
            @RequestBody final OrderRequestDto orderRequestDto) {
        OrderResponseDto orderResponseDto = orderService.addOrder(orderRequestDto);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable final Long id) {
        OrderResponseDto orderResponseDto = orderService.getOrderById(id);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderResponseDto>> getOrders() {
        List<OrderResponseDto> orderResponseDtos = orderService.getOrder();
        return new ResponseEntity<>(orderResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OrderResponseDto> deleteOrder(@PathVariable final Long id) {
        OrderResponseDto orderResponseDto = orderService.deleteOrder(id);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    private ResponseEntity<OrderResponseDto> editOrder(@PathVariable final Long id,
                                                         @RequestBody final OrderRequestDto orderRequestDto) {
        OrderResponseDto orderResponseDto = OrderService.editOrder (id, orderRequestDto);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    @PostMapping("/addCustomer/{customerId}/to/{orderId}")
    private ResponseEntity<OrderResponseDto> addCustomer(@PathVariable final Long customerId,
                                                         @PathVariable final Long orderId) {

         OrderResponseDto orderResponseDto = OrderService.addCustomerToOrder(orderId, customerId);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    @PostMapping("/removeCustomer/{id}")
    private ResponseEntity<OrderResponseDto> removeCustomer(@PathVariable final Long id) {
        OrderResponseDto orderResponseDto = orderService.deleteCustomerFromOrder(id);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }
}
