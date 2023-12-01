package com.ynov.msoperation.service;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.ynov.msaccount.entities.Account;
import com.ynov.msoperation.dao.OperationRepository;
import com.ynov.msoperation.entities.Operation;
import com.ynov.msoperation.entities.OperationType;

public class BasicOperationService implements OperationService {

    private final OperationRepository operationRepository;

    private final RestTemplate restTemplate;

    private static final String ACCOUNTS_SERVICE = "http://localhost:8082/account/v1";

    public BasicOperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Operation performOperation(Operation operation) {
        operation.setOperationType(checkOperationType(operation));
        switch(operation.getOperationType()) {
            case DEPOSIT:
            return this.operationRepository.save(performDeposit(operation));
            case WITHDRAWAL:
            return this.operationRepository.save(performWithdrawal(operation));
            case TRANSFERT:
            return this.operationRepository.save(performTransfert(operation));
            default:
            return null;
        }
    }

    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    public Operation getOperation(Long id) {
        return this.operationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Operation> deleteAllOperations() {
        List<Operation> operations = this.operationRepository.findAll();
        this.operationRepository.deleteAll();
        return operations;
    }

    @Override
    public Operation deleteOperation(Long id) {
        Operation operation = this.operationRepository.findById(id).orElse(null);
        this.operationRepository.deleteById(id);
        return operation;
    }

    private OperationType checkOperationType(Operation operation) {
        if(operation.getOperationType() == null) {
            if(operation.getSourceAccountId() != null) {
                if(operation.getTargetAccountId() != null) {
                    return OperationType.TRANSFERT;
                } else {
                    return OperationType.WITHDRAWAL;
                }
            } else {
                if(operation.getTargetAccountId() != null) {
                    return OperationType.DEPOSIT;
                } else {
                    return null;
                }
            }
        } else {
            return operation.getOperationType();
        }
    }
    
    private Operation performDeposit(Operation operation) {
        Long targetAccountId = operation.getTargetAccountId();
        Account targetAccount = restTemplate.getForObject(ACCOUNTS_SERVICE + "/" +targetAccountId, Account.class);
        if (targetAccount != null) {
            targetAccount.setBalance(targetAccount.getBalance() + operation.getAmount());
            restTemplate.put(ACCOUNTS_SERVICE + "/" + targetAccountId, targetAccount);
            return operation;
        }
        return null;
    }

    private Operation performWithdrawal(Operation operation) {
        Long sourceAccountId = operation.getTargetAccountId();
        Account sourceAccount = restTemplate.getForObject(ACCOUNTS_SERVICE + "/" + sourceAccountId, Account.class);
        if (sourceAccount != null && (sourceAccount.getBalance() - operation.getAmount() >= 0)) {
            sourceAccount.setBalance(sourceAccount.getBalance() - operation.getAmount());
            restTemplate.put(ACCOUNTS_SERVICE + "/" + sourceAccountId, sourceAccount);
            return operation;
        }
        return null;
    }

    private Operation performTransfert(Operation operation) {
        return (performWithdrawal(operation) != null) ? performDeposit(operation):null;
    }
}
