package com.ynov.msoperation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ynov.msoperation.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation,Long> {}
