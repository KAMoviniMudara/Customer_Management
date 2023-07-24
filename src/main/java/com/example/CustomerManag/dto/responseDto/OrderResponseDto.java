package com.example.CustomerManag.dto.responseDto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private String name;
    private String customerName;
    private List<String> orderNames;
}
