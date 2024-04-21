package com.finalProject.FinalProject.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
public class EmployeeDetail extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String permanentAdd;
    private String currentAdd;
    private Integer familyMembers;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeDetail", referencedColumnName = "id")
    private Set<Employee> employees;
}
