package com.finalProject.FinalProject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
public class Company extends Base{

    @Id
    private String name;
    @ManyToOne
    @JoinColumn(name = "doctorID")
    private Doctor doctorID;
    private String representativeName;
    private String representativeContact;
    private String supplyAddress;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyName", referencedColumnName = "name")
    private Set<Product> products;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyName", referencedColumnName = "name")
    private Set<ReturnedtoCompany> returnedtoCompanies;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "companyId", referencedColumnName = "name")
    private Set<Purchase> purchases;
}
