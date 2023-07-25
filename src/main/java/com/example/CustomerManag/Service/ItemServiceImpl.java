package com.example.CustomerManag.Service;

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
        if(itemRequestDto.getOrderIds().isEmpty()) {
            throw new IllegalArgumentException("you need at least on order");
        } else {
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
        return null;
    }

    @Override
    public Item getItem(Long itemId) {
        return null;
    }

    @Override
    public List<ItemResponseDto> getItems() {
        return null;
    }

    @Override
    public ItemResponseDto deleteItem(Long itemId) {
        return null;
    }

    @Override
    public ItemResponseDto editItem(Long itemId, ItemRequestDto bookRequestDto) {
        return null;
    }

    @Override
    public ItemResponseDto addOrderToItem(Long ItemId, Long orderId) {
        return null;
    }

    @Override
    public ItemResponseDto deleteOrderFromBook(Long itemId, Long orderId) {
        return null;
    }
}
