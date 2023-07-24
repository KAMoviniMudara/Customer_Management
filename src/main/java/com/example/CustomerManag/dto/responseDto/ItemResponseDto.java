package com.example.CustomerManag.dto.responseDto;

import lombok.Data;

import java.util.List;

@Data
public class ItemResponseDto {
    private Long id;
    private String name;
    private String price;
    private int qty;
    private List<String> itemNames;
}
