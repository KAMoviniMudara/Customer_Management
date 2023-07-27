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

    @Override
    public ItemResponseDto addItem(ItemRequestDto itemRequestDto) {
        Item item = new Item();
        item.setName(itemRequestDto.getName());
        itemRepository.save(item);
        return mapper.itemToItemResponseDto(item);
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
    public ItemResponseDto editItem(Long itemId, ItemRequestDto itemRequestDto) {
        Item itemToEdit = getItem(itemId);
        itemToEdit.setName(itemRequestDto.getName());
        return mapper.itemToItemResponseDto(itemToEdit);
    }

    @Override
    public ItemResponseDto addItemToOrder(Long orderId, Long itemId) {
            Item item = getItem(itemId);
            Order order= orderService.getOrder(orderId);
            if(Objects.nonNull(order.getItem())){
                throw new IllegalArgumentException("item already has a order");
            }
            order.setItem(item);
            item.addOrder(order);
            return mapper.itemToItemResponseDto(item);
    }

    @Override
    public ItemResponseDto deleteItemFromOrder(Long itemId, Long orderId) {
        Item item = getItem(itemId);
        Order order = orderService.getOrder(orderId);

        item.deleteOrder(order);
        return mapper.itemToItemResponseDto(item);


    }
}
