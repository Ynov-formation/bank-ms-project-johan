package com.ynov.msoperation.service;

import java.util.List;

import com.ynov.msoperation.entities.Operation;

public interface OperationService {
    
    Operation performOperation(Operation operation);

    List<Operation> getAllOperations();

    Operation getOperation(Long id);

    List<Operation> deleteAllOperations();

    Operation deleteOperation(Long id);
}
