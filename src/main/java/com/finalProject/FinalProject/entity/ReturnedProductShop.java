package com.finalProject.FinalProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Entity
@Getter
@Setter
public class ReturnedProductShop extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tradeName")
    private Product tradeName;
//    @ManyToOne
//    @JoinColumn(name = "companyName")
//    private Company companyName;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    private LocalDate purchaseDate;
    private LocalDate returnDate;
    private Integer returnQTY;
    @ManyToOne
    @JoinColumn(name = "fileData")
    @JsonIgnore
    private FileData fileData;
}
