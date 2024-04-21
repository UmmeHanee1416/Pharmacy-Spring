package com.finalProject.FinalProject.controller;


import com.finalProject.FinalProject.dto.ProductDTO;
import com.finalProject.FinalProject.dto.ReturnedtoCompanyDTO;
import com.finalProject.FinalProject.entity.Product;
import com.finalProject.FinalProject.entity.ReturnedtoCompany;
import com.finalProject.FinalProject.service.ReturnedtoCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retcomp")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ReturnedtoCompanyController {

    @Autowired
    private ReturnedtoCompanyService returnedtoCompanyService;

    @GetMapping
    public List<ReturnedtoCompanyDTO> getAll(){
        return returnedtoCompanyService.getAll();
    }

    @PostMapping
    public ReturnedtoCompanyDTO addData(@RequestBody ReturnedtoCompanyDTO productDTO){
        return returnedtoCompanyService.addData(productDTO);
    }

    @GetMapping("/{id}")
    public ReturnedtoCompanyDTO getByID(@PathVariable("id") Long id){
        return returnedtoCompanyService.getById(id);
    }


    @PutMapping("/{id}")
    public ReturnedtoCompany putProduct(@PathVariable ("id") Long id, @RequestBody ReturnedtoCompanyDTO productDTO){
        return returnedtoCompanyService.updateData(id,productDTO);
    }
}
