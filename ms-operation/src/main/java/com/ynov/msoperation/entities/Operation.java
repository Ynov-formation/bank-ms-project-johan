package com.ynov.msoperation.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="t_operation")
public class Operation {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    private Long sourceAccountId;
    private Long targetAccountId;
    private Float amount;
}
