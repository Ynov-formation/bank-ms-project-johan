package com.ynov.msaccount.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ynov.msaccount.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
}
