package com.finalProject.FinalProject.controller;

import com.finalProject.FinalProject.dto.EmployeeDetailDTO;
import com.finalProject.FinalProject.dto.ProductDTO;
import com.finalProject.FinalProject.dto.SalesDetailDTO;
import com.finalProject.FinalProject.entity.EmployeeDetail;
import com.finalProject.FinalProject.entity.Product;
import com.finalProject.FinalProject.entity.Sales;
import com.finalProject.FinalProject.entity.SalesDetail;
import com.finalProject.FinalProject.repository.SalesDetailRepo;
import com.finalProject.FinalProject.service.CustomerService;
import com.finalProject.FinalProject.service.SalesDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Formatter;
import java.util.List;

@RestController
@RequestMapping("/saledet")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class SalesDetailController {

    @Autowired
    private CustomerService salesDetailService;

    @Autowired
    private SalesDetailRepo salesDetailRepo;

    @GetMapping
    public List<SalesDetailDTO> getAll(){
        return salesDetailService.getAllsd();
    }

    @PostMapping
    public SalesDetailDTO addData(@RequestBody SalesDetailDTO salesDetailDTO){
        return salesDetailService.addData(salesDetailDTO);
    }

    @GetMapping("/{id}")
    public SalesDetailDTO getByID(@PathVariable("id") Long id){
        return salesDetailService.getByIdsd(id);
    }

    @GetMapping("/sales/{salesID}")
    public List<SalesDetailDTO> getBySales(@PathVariable("salesID") Long sales){
        return salesDetailService.getBySales(sales);
    }


    @PutMapping("/{id}")
    public SalesDetail putProduct(@PathVariable ("id") Long id, @RequestBody SalesDetailDTO salesDetailDTO){
        return salesDetailService.updateData(id,salesDetailDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable("id") Long id){
        salesDetailService.deleteByID(id);
    }

    @GetMapping("/date/{date}")
    public Integer getTotalCharge(@PathVariable("date") String sales){

        return 0;
    }
}
