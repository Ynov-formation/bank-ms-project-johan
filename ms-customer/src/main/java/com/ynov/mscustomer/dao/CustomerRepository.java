package com.ynov.mscustomer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ynov.mscustomer.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {}
