package com.example.CustomerManag.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="OrderRequestDto")
public class Order {
    private Long id;
    private Date date;
    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private List<Item> items = new ArrayList<>();

    public Order(Long id, Date date, Customer customer, List<Item> items) {
        this.id = id;
        this.date = date;
        this.customer = customer;

    }



}
