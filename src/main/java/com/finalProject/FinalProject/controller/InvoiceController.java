package com.finalProject.FinalProject.controller;


import com.finalProject.FinalProject.dto.InvoiceDTO;
import com.finalProject.FinalProject.entity.Invoice;
import com.finalProject.FinalProject.service.CustomerService;
import com.finalProject.FinalProject.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class InvoiceController {

    @Autowired
    private CustomerService invoiceService;

    @GetMapping
    public List<InvoiceDTO> getAll(){
        return invoiceService.getAllInv();
    }

    @PostMapping
    public InvoiceDTO addData(@RequestBody InvoiceDTO invoice){
        return invoiceService.addData(invoice);
    }

    @GetMapping("/{id}")
    public InvoiceDTO getByID(@PathVariable("id") Long id){
        return invoiceService.getByIdInv(id);
    }


    @PutMapping("/{id}")
    public Invoice putProduct(@PathVariable ("id") Long id, @RequestBody Invoice invoice){
        return invoiceService.updateData(id,invoice);
    }
}
