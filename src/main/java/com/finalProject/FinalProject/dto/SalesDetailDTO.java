package com.finalProject.FinalProject.dto;

import lombok.Data;

@Data
public class SalesDetailDTO {

    private Long id;
    private Long salesId;
    private String productId;
    private Integer productQuantity;
    private Integer totalCharge;

    //    public void setProductQuantity(Integer productQuantity) {
//        int abc = 0;
//        abc += productQuantity==null?0:productQuantity;
//        this.productQuantity = abc ;
//    }
    public Integer getTotalCharge(Integer pPrice, Integer productQuantity) {
        this.totalCharge = productQuantity * pPrice;
        return  productQuantity * pPrice;
    }
}
