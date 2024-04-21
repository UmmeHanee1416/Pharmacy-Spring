package com.finalProject.FinalProject.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Generic extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genericName;
    private Integer registeredQuantity;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "product_generic",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "generic_id"))
    private Set<Product> products = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "purchase_generic",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "generic_id"))
    private Set<Purchase> purchases = new HashSet<>();
}
