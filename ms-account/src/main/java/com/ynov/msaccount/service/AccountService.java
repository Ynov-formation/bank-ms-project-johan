package com.ynov.msaccount.service;

import java.util.List;

import com.ynov.msaccount.entities.Account;

public interface AccountService {
    
    Account createAccount(Account account);

    List<Account> getAllAccounts();

    Account getAccount(Long id);

    Account updateAccount(Long id, Account account);

    List<Account> deleteAllAccounts();

    Account deleteAccount(Long id);
}
