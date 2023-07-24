package com.example.CustomerManag.dto.requestDto;

import lombok.Data;

import java.util.List;

@Data
public class ItemRequestDto {
    private String name;
    private String price;
    private int qty;
    private List<Long> orderIds;


}
