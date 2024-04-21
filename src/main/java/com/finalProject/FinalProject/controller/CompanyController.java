package com.finalProject.FinalProject.controller;


import com.finalProject.FinalProject.dto.CompanyDTO;
import com.finalProject.FinalProject.entity.Company;
import com.finalProject.FinalProject.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<CompanyDTO> getAll(){
        return companyService.getAll();
    }

    @PostMapping
    public CompanyDTO addData(@RequestBody CompanyDTO companyDTO){
        return companyService.addData(companyDTO);
    }

    @GetMapping("/{id}")
    public CompanyDTO getByID(@PathVariable("id") String id){
        return companyService.getById(id);
    }


    @PutMapping("{id}")
    public CompanyDTO putCompany(@PathVariable ("id") Long id, @RequestBody CompanyDTO companyDTO){
        return companyService.updateData(id,companyDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable("id") String id){
        companyService.deleteByID(id);
    }
}
