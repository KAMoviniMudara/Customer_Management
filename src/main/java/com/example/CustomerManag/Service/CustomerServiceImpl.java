package com.example.CustomerManag.Service;

import com.example.CustomerManag.dto.requestDto.CustomerRequestDto;
import com.example.CustomerManag.entity.Customer;
import com.example.CustomerManag.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public Customer addCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer();
        customer.setName(customerRequestDto.getName());
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer>customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(()->
                new IllegalArgumentException(
                        "customer with customerId :" + customerId + "could not be found")).getCustomer();
    }

    @Override
    public Customer deleteCustomer(Long customerId) {
        Customer customer = getCustomer(customerId);
        customerRepository.delete(customer);
        return customer;
    }

    @Override
    public Customer editCustomer(Long customerId, CustomerRequestDto customerRequestDto) {
            Customer cityToEdit = getCustomer(customerId);
            cityToEdit.setName(customerRequestDto.getName());
            return cityToEdit;
    }
}
