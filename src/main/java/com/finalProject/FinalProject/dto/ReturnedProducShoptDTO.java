package com.finalProject.FinalProject.dto;

import com.finalProject.FinalProject.entity.Customer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ReturnedProducShoptDTO {

    private Long id;
    private String tradeName;
//    private String companyName;
    private String customer;
    private LocalDate purchaseDate;
    private LocalDate returnDate;
    private Integer returnQTY;
    private Long fileData;
}
