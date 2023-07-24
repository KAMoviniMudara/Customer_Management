package com.example.CustomerManag.dto.requestDto;

import lombok.Data;
import java.util.Date;

@Data
public class OrderRequestDto {
    private String name;
    private Date date;
    private Long customerId;
}
