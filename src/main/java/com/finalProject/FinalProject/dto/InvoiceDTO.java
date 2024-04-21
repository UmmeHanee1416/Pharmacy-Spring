package com.finalProject.FinalProject.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class InvoiceDTO {

    private Long id;
    private Long salesId;
    private String customerId;
    private Integer soldQty;
    private LocalDate saleDate;
}
