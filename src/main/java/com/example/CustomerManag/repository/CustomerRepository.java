package com.example.CustomerManag.repository;

import com.example.CustomerManag.entity.Customer;
import com.example.CustomerManag.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Order, Long> {

}
