package com.finalProject.FinalProject.entity;


import com.finalProject.FinalProject.repository.SalesDetailRepo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends Base{

    @Id
    private String tradeName;
    @ManyToOne
    @JoinColumn(name = "companyName")
    private Company companyName;
    private Integer regsrtQuantity;
    private Integer soldQuantity;
    private Integer remainedQuantity;
    private LocalDate mfd;
    private LocalDate exp;
    private Integer sellPrice;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "generic_products",
            joinColumns = @JoinColumn(name = "generic_id"),
            inverseJoinColumns = @JoinColumn(name = "product_name"))
    private Set<Generic> generics = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "tradeName")
    private Set<SalesDetail> salesDetails;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tradeName", referencedColumnName = "tradeName")
    private Set<ReturnedProductShop> returnedProductShops;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tradeName", referencedColumnName = "tradeName")
    private Set<ReturnedtoCompany> returnedtoCompanies;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "tradeName")
    private Set<Purchase> purchases;

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }
}
