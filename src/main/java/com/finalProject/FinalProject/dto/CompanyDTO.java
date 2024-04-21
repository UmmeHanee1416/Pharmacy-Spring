package com.finalProject.FinalProject.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    private Long id;
    private String name;
    private Long doctorID;
    private String representativeName;
    private String representativeContact;
    private String supplyAddress;
}
