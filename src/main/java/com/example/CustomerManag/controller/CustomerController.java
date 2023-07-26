package com.example.CustomerManag.controller;

import com.example.CustomerManag.Service.CustomerService;
import com.example.CustomerManag.dto.requestDto.CustomerRequestDto;
import com.example.CustomerManag.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody final CustomerRequestDto customerRequestDto){
        Customer customer= customerService.addCustomer(customerRequestDto);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Customer>> getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable final Long id) {
        Customer customer = customerService.deleteCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/edit/id")
    public ResponseEntity<Customer> editCustomer(@RequestBody final CustomerRequestDto customerRequestDto,
                                         @PathVariable final Long id) {
        Customer customer = customerService.editCustomer(id, customerRequestDto);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


}
