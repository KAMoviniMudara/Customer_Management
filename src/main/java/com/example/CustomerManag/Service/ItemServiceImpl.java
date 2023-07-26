package com.example.CustomerManag.Service;

import com.example.CustomerManag.dto.mapper;
import com.example.CustomerManag.dto.requestDto.ItemRequestDto;
import com.example.CustomerManag.dto.responseDto.ItemResponseDto;
import com.example.CustomerManag.entity.Item;
import com.example.CustomerManag.entity.Order;
import com.example.CustomerManag.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final OrderService orderService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, OrderService orderService) {
        this.itemRepository = itemRepository;
        this.orderService = orderService;
    }

    @Transactional
    @Override
    public ItemResponseDto addItem(ItemRequestDto itemRequestDto) {
        Item item = new Item();
        item.setName(itemRequestDto.getName());
        if(itemRequestDto.getOrderId()==null) {
            return itemRepository.save(item);
        }

            List<Order> orders = new ArrayList();
            for (Long orderId: itemRequestDto.getOrderIds()){
                Order order = orderService.getOrder(orderId);
                orders.add(order);
            }
            item.setOrders(orders);
        }

    }

    @Override
    public ItemResponseDto getItemById(Long itemId) {
        Item item = getItem(itemId);
        return mapper.itemToItemResponseDto(item);
    }

    @Override
    public Item getItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new IllegalArgumentException("cannot find item with id: " + itemId));
        return item;
    }

    @Override
    public List<ItemResponseDto> getItems() {
        List<Item>items = StreamSupport
                .stream(itemRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return mapper.itemsToItemResponseDto(items);
    }

    @Override
    public ItemResponseDto deleteItem(Long itemId) {
        Item item = getItem(itemId);
        itemRepository.delete(item);
        return mapper.itemToItemResponseDto(item);
    }

    @Override
    public ItemResponseDto editItem(Long itemId, ItemRequestDto bookRequestDto) {
        Item itemToEdit = getItem(itemId);
        itemToEdit.setName(itemRequestDto.getName());
        if (!itemRequestDto.getOrderIds().isEmpty()) {
            List<Order> orders = new ArrayList<>();
            for (Long orderId : itemRequestDto.getOrderIds()) {
                Order author = orderService.getOrder(orderId);
                orders.add(order);
            }
            itemToEdit.setOrders(orders);
        }
    }

    @Override
    public ItemResponseDto addOrderToItem(Long ItemId, Long orderId) {
            Item item = getItem(ItemId);
            Order order= orderService.getOrder(orderId);

            item.setOrder(order);
            return item;
    }

    @Override
    public ItemResponseDto deleteOrderFromBook(Long itemId, Long orderId) {
        Item item = getItem(ItemId);
        Order order = orderService.getOrder(OrderId);

        order.deleteItem(item);
        item.deleteOrder(order);
        return mapper.itemToItemResponseDto(item);


    }
}
