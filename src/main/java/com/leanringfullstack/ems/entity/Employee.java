package com.leanringfullstack.ems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "employees")
@Entity
@Data
public class Employee extends IdBasedEntity{
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
}
