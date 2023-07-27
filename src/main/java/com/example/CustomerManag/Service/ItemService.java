package com.example.CustomerManag.Service;

import com.example.CustomerManag.dto.requestDto.ItemRequestDto;
import com.example.CustomerManag.dto.responseDto.ItemResponseDto;
import com.example.CustomerManag.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    public ItemResponseDto addItem(ItemRequestDto itemRequestDto);
    public ItemResponseDto getItemById(Long itemId);
    public Item getItem(Long itemId);
    public List<ItemResponseDto> getItems();
    public ItemResponseDto deleteItem(Long itemId);
    public ItemResponseDto editItem(Long itemId, ItemRequestDto bookRequestDto);
    public ItemResponseDto addItemToOrder(Long ItemId, Long orderId);
    public ItemResponseDto deleteItemFromOrder (Long itemId, Long orderId);
}
