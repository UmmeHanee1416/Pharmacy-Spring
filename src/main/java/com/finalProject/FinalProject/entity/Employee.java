package com.finalProject.FinalProject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Employee extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer experience;
    private String education;
    @ManyToOne
    @JoinColumn(name = "employeeDetail")
    private EmployeeDetail employeeDetail;
    private String reference;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "empId", referencedColumnName = "id")
    private Set<Sales> purchases;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    private Set<Customer> customers;
}
