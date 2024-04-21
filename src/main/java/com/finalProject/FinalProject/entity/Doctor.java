package com.finalProject.FinalProject.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Doctor extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialty;
    private String address;
    private String contact;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctorID", referencedColumnName = "id")
    private Set<Company> companies;
}
