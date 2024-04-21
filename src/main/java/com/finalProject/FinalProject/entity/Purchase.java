package com.finalProject.FinalProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
public class Purchase extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product productId;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "generic_purchase",
            joinColumns = @JoinColumn(name = "generic_id"),
            inverseJoinColumns = @JoinColumn(name = "purchase_id"))
    private Set<Generic> generics = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company companyId;
    private String batchId;
    private Integer purchaseQuantity;
    private LocalDate purchaseDate;
    private Integer MRP;

}
