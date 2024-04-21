package com.finalProject.FinalProject.controller;


import com.finalProject.FinalProject.dto.GenericDTO;
import com.finalProject.FinalProject.dto.ProductDTO;
import com.finalProject.FinalProject.entity.Generic;
import com.finalProject.FinalProject.entity.Product;
import com.finalProject.FinalProject.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generic")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class GenericController {

    @Autowired
    private GenericService genericService;

    @GetMapping
    public List<GenericDTO> getAll(){
        return genericService.getAll();
    }

    @PostMapping
    public Generic addData(@RequestBody GenericDTO productDTO){
        return genericService.addData(productDTO);
    }

    @GetMapping("/{id}")
    public GenericDTO getByID(@PathVariable("id") Long id){
        return genericService.getById(id);
    }

    @PutMapping("/{id}")
    public Generic putProduct(@PathVariable ("id") Long id, @RequestBody GenericDTO productDTO){
        return genericService.updateData(id,productDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable("id") Long id){
        genericService.deleteByID(id);
    }
}
