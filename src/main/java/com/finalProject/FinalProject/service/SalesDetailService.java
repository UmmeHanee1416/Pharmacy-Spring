package com.finalProject.FinalProject.service;

import com.finalProject.FinalProject.dto.ProductDTO;
import com.finalProject.FinalProject.dto.SalesDetailDTO;
import com.finalProject.FinalProject.entity.Company;
import com.finalProject.FinalProject.entity.Product;
import com.finalProject.FinalProject.entity.Sales;
import com.finalProject.FinalProject.entity.SalesDetail;
import com.finalProject.FinalProject.repository.ProductRepo;
import com.finalProject.FinalProject.repository.SalesDetailRepo;
import com.finalProject.FinalProject.repository.SalesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesDetailService {

    @Autowired
    private SalesDetailRepo salesDetailRepo;

    @Autowired
    private SalesRepo salesRepo;

    @Autowired
    private ProductRepo productRepo;

    public List<SalesDetailDTO> getAll(){
        List<SalesDetail> salesDetails = salesDetailRepo.findAll();
        List<SalesDetailDTO> salesDetailDTOS = new ArrayList<>();
        for (SalesDetail s: salesDetails) {
            SalesDetailDTO salesDetailDTO = new SalesDetailDTO();
            maptoDTO(s,salesDetailDTO);
            salesDetailDTOS.add(salesDetailDTO);
        }
        return salesDetailDTOS;
    }


    public SalesDetail addData(SalesDetailDTO salesDetailDTO){
        SalesDetail salesDetail = new SalesDetail();
        maptoEntity(salesDetailDTO,salesDetail);
        return salesDetailRepo.save(salesDetail);
    }

    public SalesDetail updateData(Long id,SalesDetailDTO salesDetailDTO){
        SalesDetail salesDetail = new SalesDetail();
        maptoEntity(salesDetailDTO,salesDetail);
        return salesDetailRepo.save(salesDetail);
    }

    public SalesDetailDTO getById(Long id){
        SalesDetail salesDetail = salesDetailRepo.findById(id).get();
        SalesDetailDTO salesDetailDTO = new SalesDetailDTO();
        maptoDTO(salesDetail,salesDetailDTO);
        return salesDetailDTO;
    }

    public void deleteByID(Long id){
        salesDetailRepo.deleteById(id);
    }

    public SalesDetailDTO maptoDTO(SalesDetail salesDetail,SalesDetailDTO salesDetailDTO){
        if (salesDetailDTO!=null){
            salesDetailDTO.setId(salesDetail.getId());
        }
        salesDetailDTO.setSalesId(maptoPurchaseID(salesDetail));
        salesDetailDTO.setProductId(maptoProductID(salesDetail));
//        salesDetailDTO.setProductQuantity(salesDetail.getProductQuantity());
        return salesDetailDTO;
    }

    public SalesDetail maptoEntity(SalesDetailDTO salesDetailDTO,SalesDetail salesDetail){
        if (salesDetail!=null){
            salesDetail.setId(salesDetailDTO.getId());
        }
        salesDetail.setSalesId(maptoSales(salesDetailDTO));
        salesDetail.setProductId(maptoProduct(salesDetailDTO));
//        salesDetail.setProductQuantity(salesDetailDTO.getProductQuantity());
        return salesDetail;
    }

    public Sales maptoSales(SalesDetailDTO salesDetailDTO){
        Sales sales = salesRepo.findById(salesDetailDTO.getSalesId()).get();
        return sales;
    }

    public Product maptoProduct(SalesDetailDTO salesDetailDTO){
        Product product = productRepo.findById(salesDetailDTO.getProductId()).get();
        return product;
    }

    public Long maptoPurchaseID(SalesDetail salesDetail){
        Long l = salesDetail.getSalesId().getId();
        return l;
    }

    public String maptoProductID(SalesDetail salesDetail){
        String s = salesDetail.getProductId().getTradeName();
        return s;
    }
}
