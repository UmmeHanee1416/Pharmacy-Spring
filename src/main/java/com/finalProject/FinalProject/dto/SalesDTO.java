package com.finalProject.FinalProject.dto;

import lombok.Data;

@Data
public class SalesDTO {


    private Long id;
    private String customerId;
    private  Integer totalQuantity;
    private Integer totalCharge;
    private Long empId;
    private String empName;
//    public Integer setTotalQuantity(SalesDetailDTO salesDetail) {
//            this.totalQuantity = 0;
//            this.totalQuantity += salesDetail.getProductQuantity();
//        return this.totalQuantity;
//    }
}
