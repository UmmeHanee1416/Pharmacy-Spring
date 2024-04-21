package com.finalProject.FinalProject.controller;


import com.finalProject.FinalProject.dto.SalesDTO;
import com.finalProject.FinalProject.entity.Sales;
import com.finalProject.FinalProject.service.CustomerService;
import com.finalProject.FinalProject.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class SalesController {

    @Autowired
    private CustomerService salesService;

    @Autowired
    private SalesService salesService1;

    @GetMapping
    public List<SalesDTO> getAll(){
        return salesService.getAllSales();
    }

    @PostMapping
    public SalesDTO addData(@RequestBody SalesDTO salesDTO){
        return salesService.addData(salesDTO);
    }

    @GetMapping("/{id}")
    public SalesDTO getByID(@PathVariable("id") Long id){
        return salesService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable("id") String id){
        salesService.deleteByID(id);
    }

//    @PutMapping("/{id}")
//    public SalesDTO putProduct(@PathVariable ("id") Long id, @RequestBody SalesDTO salesDTO){
//        return salesService.updateData(id,salesDTO);
//    }

    @GetMapping("/weekDay/{wd}")
    public Integer showday(@PathVariable("wd") String wd){
       return salesService1.dayOfWeek(wd);
    }
}
