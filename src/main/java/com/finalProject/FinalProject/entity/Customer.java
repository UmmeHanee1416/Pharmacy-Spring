package com.finalProject.FinalProject.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Customer extends Base{

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String name;
    @Id
    private String contact;
    @Column(updatable = false)
    @CurrentTimestamp
    private LocalDate purchaseDate;
    private String payMethod;
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee empId;
//    private Integer totalPay;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "contact")
    private Set<Sales> purchases;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer", referencedColumnName = "contact")
    private Set<ReturnedProductShop> returnedProductShops;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "contact")
    private Set<Invoice> invoices;
}
