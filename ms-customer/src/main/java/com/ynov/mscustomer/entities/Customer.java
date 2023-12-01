package com.ynov.mscustomer.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
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
@Table(name="t_customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String lastName;
    private String firstName;
}
