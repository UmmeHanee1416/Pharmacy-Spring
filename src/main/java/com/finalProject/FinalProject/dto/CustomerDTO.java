package com.finalProject.FinalProject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
public class CustomerDTO {

//    private Long id;
    private String name;
    private String contact;
    private LocalDate purchaseDate;
    private String payMethod;
    private Long empId;
    private List<SalesDetailDTO> salesDetailDTOS;

}
