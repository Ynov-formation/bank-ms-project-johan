package com.ynov.mscustomer.service;

import java.util.List;
import java.util.Optional;

import com.ynov.mscustomer.dao.CustomerRepository;
import com.ynov.mscustomer.entities.Customer;

public class BasicCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;

    public BasicCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> updateCustomer = this.customerRepository.findById(id);

        if (updateCustomer.isPresent()) {
            customer.setId(id);
            return this.customerRepository.save(customer);
        } else {
            return null;
        }
    }

    @Override
    public List<Customer> deleteAllCustomers() {
        List<Customer> customers = this.customerRepository.findAll();
        this.customerRepository.deleteAll();
        return customers;
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Customer customer = this.customerRepository.findById(id).orElse(null);
        this.customerRepository.deleteById(id);
        return customer;
    }
}
