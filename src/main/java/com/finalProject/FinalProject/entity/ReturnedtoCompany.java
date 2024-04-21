package com.finalProject.FinalProject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;


@Entity
@Getter
@Setter
public class ReturnedtoCompany extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tradeName")
    private Product tradeName;
    @ManyToOne
    @JoinColumn(name = "companyName")
    private Company companyName;
    @UpdateTimestamp
    private LocalDate returnedDate;
    private Integer returnedAmount;
}
