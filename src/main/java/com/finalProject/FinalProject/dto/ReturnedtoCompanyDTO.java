package com.finalProject.FinalProject.dto;

import com.finalProject.FinalProject.entity.Company;
import com.finalProject.FinalProject.entity.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@Setter
@ToString
public class ReturnedtoCompanyDTO {

    private Long id;
    private String tradeName;
    private String companyName;
    private LocalDate returnedDate;
    private Integer returnedAmount;

}
