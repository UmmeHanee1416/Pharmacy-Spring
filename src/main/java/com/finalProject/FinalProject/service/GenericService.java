package com.finalProject.FinalProject.service;


import com.finalProject.FinalProject.dto.GenericDTO;
import com.finalProject.FinalProject.entity.Generic;
import com.finalProject.FinalProject.repository.GenericRepo;
import com.finalProject.FinalProject.repository.ProductRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenericService {

    @Autowired
    private GenericRepo genericRepo;

    @Autowired
    private ProductRepo productRepo;

    public List<GenericDTO> getAll(){
        List<Generic> Saless = genericRepo.findAll();
        List<GenericDTO> SalesDTOS = new ArrayList<>();
        for (Generic s: Saless) {
            GenericDTO SalesDTO = new GenericDTO();
            BeanUtils.copyProperties(s,SalesDTO);
            SalesDTOS.add(SalesDTO);
        }
        return SalesDTOS;
    }


    public Generic addData(GenericDTO genericDTO){
        Generic generic = new Generic();
        BeanUtils.copyProperties(genericDTO,generic);
        return genericRepo.save(generic);
    }

    public Generic updateData(Long id, GenericDTO genericDTO){
        Generic generic = new Generic();
        BeanUtils.copyProperties(genericDTO,generic);
        return genericRepo.save(generic);
    }

    public GenericDTO getById(Long id){
        Generic sales = genericRepo.findById(id).get();
        GenericDTO salesDTO = new GenericDTO();
        BeanUtils.copyProperties(sales,salesDTO);
        return salesDTO;
    }

    public void deleteByID(Long id){
        genericRepo.deleteById(id);
    }

}
