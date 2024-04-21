package com.finalProject.FinalProject.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Invoice extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "salesId")
    private Sales salesId;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customerId;
    private Integer soldQty;
    private LocalDate saleDate;

}
