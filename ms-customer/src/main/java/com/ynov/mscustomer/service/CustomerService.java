package com.ynov.mscustomer.service;

import java.util.List;

import com.ynov.mscustomer.entities.Customer;

public interface CustomerService {
    
    Customer createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomer(Long id);

    Customer updateCustomer(Long id, Customer customer);

    List<Customer> deleteAllCustomers();

    Customer deleteCustomer(Long id);
}
