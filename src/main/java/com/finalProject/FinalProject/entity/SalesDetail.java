package com.finalProject.FinalProject.entity;

import com.finalProject.FinalProject.dto.SalesDetailDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SalesDetail extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "salesId")
    private Sales salesId;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product productId;
    private Integer productQuantity;
    private Integer totalCharge;

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

//    public void setProductQuantity(SalesDetailDTO salesDetailDTO) {
//        this.productQuantity += salesDetailDTO.getProductQuantity();
//    }

    public void setTotalCharge(Product product, Integer productQuantity) {
        this.totalCharge = productQuantity*product.getSellPrice();
    }
    public Integer getTotalCharge(Product product, Integer productQuantity) {
        return this.totalCharge = productQuantity*product.getSellPrice();
    }
}
