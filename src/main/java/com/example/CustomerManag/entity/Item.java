package com.example.CustomerManag.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="Customer")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int qty;
    private String price;
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(
            name = "item_order",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders = new ArrayList<>();

    public Item(Long id, String name, int qty, String price, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.orders = orders;
    }
    public void addOrder(Order order){
        orders.add(order);
    }

    public void deleteOrder(Order order){
        orders.remove(order);
    }
}
