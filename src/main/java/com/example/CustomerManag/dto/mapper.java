package com.example.CustomerManag.dto;

import com.example.CustomerManag.dto.responseDto.ItemResponseDto;
import com.example.CustomerManag.dto.responseDto.OrderResponseDto;
import com.example.CustomerManag.entity.Item;
import com.example.CustomerManag.entity.Order;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class mapper {
    public static OrderResponseDto orderToOrderResponseDto(Order order){
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        List<String>names = new ArrayList<>();
        List<Book> items = order.getItems();
        for (Item item:items){
            names.add(item.getName());
        }
        orderResponseDto.setItemNames(names);
        return orderResponseDto;

    }
    public static List<OrderResponseDto> ordersToOrderResponseDto(List<Item> items){
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        for (Order order : orders){
            orderResponseDtos.add(orderToOrderResponseDto(order));
        }
        return orderResponseDtos;
    }
}
