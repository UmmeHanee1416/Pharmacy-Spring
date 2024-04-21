package com.finalProject.FinalProject.service;


import com.finalProject.FinalProject.dto.ProductDTO;
import com.finalProject.FinalProject.dto.ReturnedtoCompanyDTO;
import com.finalProject.FinalProject.entity.Company;
import com.finalProject.FinalProject.entity.Generic;
import com.finalProject.FinalProject.entity.Product;
import com.finalProject.FinalProject.entity.ReturnedtoCompany;
import com.finalProject.FinalProject.repository.CompanyRepo;
import com.finalProject.FinalProject.repository.GenericRepo;
import com.finalProject.FinalProject.repository.ProductRepo;
import com.finalProject.FinalProject.repository.ReturnedtoCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReturnedtoCompanyService {

    @Autowired
    private ReturnedtoCompanyRepo returnedtoCompanyRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private GenericRepo genericRepo;

    public List<ReturnedtoCompanyDTO> getAll(){
        List<ReturnedtoCompany> products = returnedtoCompanyRepo.findAll();
        List<ReturnedtoCompanyDTO> productDTOS = new ArrayList<>();
        for (ReturnedtoCompany p: products) {
            ReturnedtoCompanyDTO productDTO = new ReturnedtoCompanyDTO();
            maptoDTO(p,productDTO);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    public ReturnedtoCompanyDTO addData(ReturnedtoCompanyDTO returnedtoCompanyDTO){
        ReturnedtoCompany returnedtoCompany = new ReturnedtoCompany();
        Product product = productRepo.findById(returnedtoCompanyDTO.getTradeName()).get();
        productRepo.updateRemainQTY(product.getRemainedQuantity() - returnedtoCompanyDTO.getReturnedAmount(), product.getTradeName());
        returnedtoCompanyDTO.setCompanyName(product.getCompanyName().getName());
        Set<Generic> generics = product.getGenerics();
        for (Generic g: generics) {
            genericRepo.updateGenericRegister((g.getRegisteredQuantity()!=null?g.getRegisteredQuantity():0)-(returnedtoCompanyDTO.getReturnedAmount()), g.getId());
        }
        maptoEntity(returnedtoCompanyDTO,returnedtoCompany);
        return maptoDTO(returnedtoCompanyRepo.save(returnedtoCompany), new ReturnedtoCompanyDTO());
    }


    public ReturnedtoCompany updateData(Long id,ReturnedtoCompanyDTO returnedtoCompanyDTO){
        ReturnedtoCompany returnedtoCompany = new ReturnedtoCompany();
        maptoEntity(returnedtoCompanyDTO,returnedtoCompany);
        return returnedtoCompanyRepo.save(returnedtoCompany);
    }

    public ReturnedtoCompanyDTO getById(Long id){
        ReturnedtoCompany returnedtoCompany = returnedtoCompanyRepo.findById(id).get();
        ReturnedtoCompanyDTO returnedtoCompanyDTO = new ReturnedtoCompanyDTO();
        maptoDTO(returnedtoCompany,returnedtoCompanyDTO);
        return returnedtoCompanyDTO;
    }

    public void deleteByID(Long id){
        returnedtoCompanyRepo.deleteById(id);
    }

    public Company maptoCompany(ReturnedtoCompanyDTO returnedtoCompanyDTO){
        Company company = companyRepo.findById(returnedtoCompanyDTO.getCompanyName()).get();
        return company;
    }

    public String maptoCompanyName(ReturnedtoCompany returnedtoCompany){
        String productDTO = companyRepo.findById(returnedtoCompany.getCompanyName().getName()).get().getName();
        return productDTO;
    }

    public ReturnedtoCompany maptoEntity(ReturnedtoCompanyDTO returnedtoCompanyDTO,ReturnedtoCompany returnedtoCompany){
        returnedtoCompany.setTradeName(productRepo.findById(returnedtoCompanyDTO.getTradeName()).get());
        returnedtoCompany.setCompanyName(maptoCompany(returnedtoCompanyDTO));
        returnedtoCompany.setReturnedDate(returnedtoCompanyDTO.getReturnedDate());
        returnedtoCompany.setReturnedAmount(returnedtoCompanyDTO.getReturnedAmount());
        return returnedtoCompany;
    }

    public ReturnedtoCompanyDTO maptoDTO(ReturnedtoCompany returnedtoCompany,ReturnedtoCompanyDTO returnedtoCompanyDTO){
        returnedtoCompanyDTO.setTradeName(productRepo.findById(returnedtoCompany.getTradeName().getTradeName()).get().getTradeName());
        returnedtoCompanyDTO.setCompanyName(maptoCompanyName(returnedtoCompany));
        returnedtoCompanyDTO.setReturnedDate(returnedtoCompany.getReturnedDate());
        returnedtoCompanyDTO.setReturnedAmount(returnedtoCompany.getReturnedAmount());
        return returnedtoCompanyDTO;
    }

}
