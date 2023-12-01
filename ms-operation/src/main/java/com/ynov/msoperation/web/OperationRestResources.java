package com.ynov.msoperation.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.msoperation.entities.Operation;
import com.ynov.msoperation.service.OperationService;

@RestController
@RequestMapping("/operation/v1")
public class OperationRestResources {
    
    private final OperationService operationService;

    public OperationRestResources(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping
    public ResponseEntity<Operation> performOperation(@RequestBody Operation operation) {
        Operation createdOperation = operationService.performOperation(operation);
        return createdOperation == null ? ResponseEntity.ok(createdOperation) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public List<Operation> getAllOperations() {
        return operationService.getAllOperations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operation> getOperation(@PathVariable(name="id") Long id) {
        Operation operation = this.operationService.getOperation(id);
        return operation != null ? ResponseEntity.ok(operation):ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public List<Operation> deleteOperations() {
        return this.operationService.deleteAllOperations();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Operation> deleteOperation(@PathVariable(name="id") Long id) {
        Operation deletedOperation = this.operationService.deleteOperation(id);
        return deletedOperation != null ? ResponseEntity.ok(deletedOperation):ResponseEntity.notFound().build();
    }
}