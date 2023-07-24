package com.example.CustomerManag.dto;

import com.example.CustomerManag.dto.responseDto.ItemResponseDto;
import com.example.CustomerManag.dto.responseDto.OrderResponseDto;
import com.example.CustomerManag.entity.Item;
import com.example.CustomerManag.entity.Order;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class mapper {
    public static ItemResponseDto itemToItemResponseDto(Item item){
        ItemResponseDto itemResponseDto = new ItemResponseDto();
        itemResponseDto.setId(item.getId());
        List<String>names = new ArrayList<>();
        List<Order> orders  = item.getOrders();
        for (Order order : orders){
            names.add(item.getName());
        }
        itemResponseDto.setItemNames(names);
        return itemResponseDto;

    }
    public static List<ItemResponseDto> itemsToItemResponseDto(List<Item> items){
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for (Item item : items){
            itemResponseDtos.add(itemToItemResponseDto(item));
        }
        return itemResponseDtos;
    }

    public static OrderResponseDto orderToOrderResponseDto(Order order){
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        List<String>names = new ArrayList<>();
        List<Item> items = order.getItems();
        for (Item item:items){
            names.add(item.getName());
        }
        orderResponseDto.setOrderNames(names);
        return orderResponseDto;

    }
    public static List<OrderResponseDto> ordersToOrderResponseDto(List<Order> orders){
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        for (Order order : orders){
            orderResponseDtos.add(orderToOrderResponseDto(order));
        }
        return orderResponseDtos;
    }
}
