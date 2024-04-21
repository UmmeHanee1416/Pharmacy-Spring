package com.finalProject.FinalProject.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor
public class PurchaseDTO {

    private Long id;
    private String productId;
    private Set<Long> generics = new HashSet<>();
    private String companyId;
    private String batchId;
    private Integer purchaseQuantity;
    private LocalDate purchaseDate;
    private Integer MRP;

}
