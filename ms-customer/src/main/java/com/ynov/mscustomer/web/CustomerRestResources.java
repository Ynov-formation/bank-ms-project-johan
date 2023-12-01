package com.ynov.mscustomer.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.mscustomer.entities.Customer;
import com.ynov.mscustomer.service.CustomerService;

@RestController
@RequestMapping("/customer/v1")
public class CustomerRestResources {
    
    private final CustomerService customerService;

    public CustomerRestResources(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return createdCustomer != null ? ResponseEntity.ok(createdCustomer) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(name="id") Long id) {
        Customer customer = customerService.getCustomer(id);
        return customer != null ? ResponseEntity.ok(customer):ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(name="id") Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = this.customerService.updateCustomer(id, customer);
        return updatedCustomer != null ? ResponseEntity.ok(updatedCustomer) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    public List<Customer> DeleteCustomers() {
        return this.customerService.deleteAllCustomers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> DeleteCustomer(@PathVariable(name="id") Long id) {
        Customer deletedCustomer = this.customerService.deleteCustomer(id);
        return deletedCustomer != null ? ResponseEntity.ok(deletedCustomer):ResponseEntity.notFound().build();
    }
}
