package com.ynov.msaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ynov.msaccount.dao.AccountRepository;
import com.ynov.msaccount.entities.Account;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BasicAccountService implements AccountService {

    private final AccountRepository accountRepository;

    public BasicAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }

    @Override
    public Account getAccount(Long id) {
        return this.accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        Optional<Account> updateAccount = this.accountRepository.findById(id);
        
        if (updateAccount.isPresent()) {
            account.setId(id);
            return this.accountRepository.save(account);
        } else {
            return null;
        }
    }

    @Override
    public List<Account> deleteAllAccounts() {
        List<Account> accounts = this.accountRepository.findAll();
        this.accountRepository.deleteAll();
        return accounts;
    }

    @Override
    public Account deleteAccount(Long id) {
        Account account =  this.accountRepository.findById(id).orElse(null);
        this.accountRepository.deleteById(id);
        return account;
    }
}
