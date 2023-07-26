package com.example.CustomerManag.controller;

import com.example.CustomerManag.Service.ItemService;
import com.example.CustomerManag.dto.requestDto.ItemRequestDto;
import com.example.CustomerManag.dto.responseDto.ItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public ResponseEntity<ItemResponseDto> addItem(@RequestBody final ItemRequestDto itemRequestDto) {
        ItemResponseDto itemResponseDto = itemService.addItem(itemRequestDto);
        return new ResponseEntity<>(itemResponseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ItemResponseDto> getItem(@PathVariable final Long id) {
        ItemResponseDto itemResponseDto = itemService.getItemById(id);
        return new ResponseEntity<>(itemResponseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ItemResponseDto>> getItems() {
        List<ItemResponseDto> itemResponseDtos = itemService.getItems();
        return new ResponseEntity<>(itemResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ItemResponseDto> deleteBook(@PathVariable final Long id) {
        ItemResponseDto itemResponseDto = itemService.deleteItem(id);
        return new ResponseEntity<>(itemResponseDto, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<ItemResponseDto> editItem(@RequestBody final ItemRequestDto itemRequestDto,
                                                    @PathVariable final Long id) {
        ItemResponseDto itemResponseDto = itemService.editItem(id, itemRequestDto);
        return new ResponseEntity<>(itemResponseDto, HttpStatus.OK);
    }

    @PostMapping("/addOrder/{orderId}/to/{itemId}")
    public ResponseEntity<ItemResponseDto> addOrder(@PathVariable final Long orderId,
                                                     @PathVariable final Long itemId) {
        ItemResponseDto itemResponseDto = itemService.addOrderToItem(itemId, orderId);
        return new ResponseEntity<>(itemResponseDto, HttpStatus.OK);
    }

    @PostMapping("/removeOrder/{orderId}/from/{itemId}")
    public ResponseEntity<ItemResponseDto> removeOrder(@PathVariable final Long orderId,
                                                        @PathVariable final Long itemId) {
        ItemResponseDto itemResponseDto = itemService.deleteOrderFromItem(itemId, orderId);
        return new ResponseEntity<>(itemResponseDto, HttpStatus.OK);
    }
}
