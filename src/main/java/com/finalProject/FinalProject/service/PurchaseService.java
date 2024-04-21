package com.finalProject.FinalProject.service;


import com.finalProject.FinalProject.dto.PurchaseDTO;
import com.finalProject.FinalProject.entity.Generic;
import com.finalProject.FinalProject.entity.Product;
import com.finalProject.FinalProject.entity.Purchase;
import com.finalProject.FinalProject.repository.CompanyRepo;
import com.finalProject.FinalProject.repository.GenericRepo;
import com.finalProject.FinalProject.repository.ProductRepo;
import com.finalProject.FinalProject.repository.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private GenericRepo genericRepo;

    public List<PurchaseDTO> getAll(){
        List<Purchase> products = purchaseRepo.findAll();
        List<PurchaseDTO> purchaseDTOS = new ArrayList<>();
        for (Purchase p: products) {
            PurchaseDTO purchaseDTO = new PurchaseDTO();
            maptoDTO(p,purchaseDTO);
            purchaseDTOS.add(purchaseDTO);
        }
        return purchaseDTOS;
    }

    public PurchaseDTO addData(PurchaseDTO purchaseDTO){
        Purchase purchase = new Purchase();
        Product product = productRepo.findById(purchaseDTO.getProductId()).get();
        productRepo.updateProductRegisterAndRemainQTY(product.getRegsrtQuantity() + purchaseDTO.getPurchaseQuantity(), (product.getRegsrtQuantity() + purchaseDTO.getPurchaseQuantity())-(product.getSoldQuantity()!=null?product.getSoldQuantity():0), product.getTradeName());
        purchase.setCompanyId(product.getCompanyName());
        purchaseDTO .setGenerics(product.getGenerics() == null ? null : product.getGenerics().stream()
                .map(Generic::getId)
                .collect(Collectors.toSet()));
        maptoEntity(purchaseDTO,purchase);
        maptoDTO(purchaseRepo.save(purchase), new PurchaseDTO());
        Set<Generic> generics = product.getGenerics();
        for (Generic g: generics) {
            genericRepo.updateGenericRegister((g.getRegisteredQuantity()!=null?g.getRegisteredQuantity():0)+((product.getRegsrtQuantity() + purchaseDTO.getPurchaseQuantity())-(product.getSoldQuantity()!=null?product.getSoldQuantity():0)), g.getId());
        }
        return null;
    }

    public PurchaseDTO updateData(Long id,PurchaseDTO purchaseDTO){
        Purchase purchase = new Purchase();
        maptoEntity(purchaseDTO,purchase);
        Product product = productRepo.findById(purchaseDTO.getProductId()).get();
//        productRepo.updateProductRegister(product.getRegsrtQuantity() + purchase.getPurchaseQuantity(), product.getTradeName());
        return maptoDTO(purchaseRepo.save(purchase), new PurchaseDTO());
    }

    public PurchaseDTO getById(Long id){
        Purchase purchase = purchaseRepo.findById(id).get();
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        maptoDTO(purchase,purchaseDTO);
        return purchaseDTO;
    }

    public void deleteByID(Long id){
        purchaseRepo.deleteById(id);
    }

    public Purchase maptoEntity(PurchaseDTO purchaseDTO,Purchase purchase){
        if (purchase!=null){
            purchase.setId(purchaseDTO.getId());
        }
        purchase.setProductId(productRepo.findById(purchaseDTO.getProductId()).get());
        purchase.setBatchId(purchaseDTO.getBatchId());
        purchase.setPurchaseQuantity(purchaseDTO.getPurchaseQuantity());
        purchase.setPurchaseDate(purchaseDTO.getPurchaseDate());
        final List<Generic> generics = genericRepo.findAllById(purchaseDTO.getGenerics() == null ? Collections.emptyList() : purchaseDTO.getGenerics());
        if (generics.size() != (purchaseDTO.getGenerics() == null ? 0 : purchaseDTO.getGenerics().size())) {
            throw new RuntimeException("one of generics not found");
        }
        purchase.setGenerics(new HashSet<>(generics));
        purchase.setMRP(purchaseDTO.getMRP());
        return purchase;
    }

    public PurchaseDTO maptoDTO(Purchase purchase,PurchaseDTO purchaseDTO){
        if (purchaseDTO!=null){
            purchaseDTO.setId(purchase.getId());
        }
        purchaseDTO.setProductId(purchase.getProductId().getTradeName());
        purchaseDTO.setCompanyId(purchase.getCompanyId().getName());
        purchaseDTO.setBatchId(purchase.getBatchId());
        purchaseDTO.setPurchaseQuantity(purchase.getPurchaseQuantity());
        purchaseDTO.setPurchaseDate(purchase.getPurchaseDate());
        purchaseDTO .setGenerics(purchase.getGenerics() == null ? null : purchase.getGenerics().stream()
                .map(Generic::getId)
                .collect(Collectors.toSet()));
        purchaseDTO.setMRP(purchase.getMRP());
        return purchaseDTO;
    }
}
