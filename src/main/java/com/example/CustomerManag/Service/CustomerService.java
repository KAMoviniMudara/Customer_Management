package com.example.CustomerManag.Service;

import com.example.CustomerManag.dto.requestDto.CustomerRequestDto;
import com.example.CustomerManag.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    public Customer addCustomer(CustomerRequestDto customerRequestDto);
    public List<Customer> getCustomers();
    public Customer getCustomer(Long customerId);
    public Customer deleteCustomer(Long customerId);
    public Customer editCustomer(Long customerId , CustomerRequestDto customerRequestDto);


}
