package com.ynov.msaccount.web;

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

import com.ynov.msaccount.entities.Account;
import com.ynov.msaccount.service.AccountService;

@RestController
@RequestMapping("/account/v1")
public class AccountRestResources {
    
    private final AccountService accountService;

    public AccountRestResources(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return createdAccount != null ? ResponseEntity.ok(createdAccount):ResponseEntity.badRequest().build();
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return this.accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable(name="id") Long id) {
        Account account = this.accountService.getAccount(id);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable(name="id") Long id, @RequestBody Account account) {
        Account updatedAccount = this.accountService.updateAccount(id, account);
        return updatedAccount != null ? ResponseEntity.ok(updatedAccount) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    public List<Account> deleteAllAccounts() {
        return this.accountService.deleteAllAccounts();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable(name="id") Long id) {
        Account deletedAccount = this.accountService.deleteAccount(id);
        return deletedAccount != null ? ResponseEntity.ok(deletedAccount):ResponseEntity.notFound().build();
    }
}
