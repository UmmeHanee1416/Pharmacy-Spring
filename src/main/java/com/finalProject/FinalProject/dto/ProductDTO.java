package com.finalProject.FinalProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finalProject.FinalProject.repository.SalesDetailRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
public class ProductDTO {

    private String tradeName;
    private String companyName;
    private Integer regsrtQuantity;
    private Integer soldQuantity;
    private Integer remainedQuantity;
    private LocalDate mfd;
    private LocalDate exp;
    private Integer sellPrice;
//    @JsonIgnore
    private Set<Long> generics = new HashSet<>();

}
