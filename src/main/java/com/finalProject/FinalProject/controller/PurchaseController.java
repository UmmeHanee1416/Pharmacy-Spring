package com.finalProject.FinalProject.controller;

import com.finalProject.FinalProject.dto.ProductDTO;
import com.finalProject.FinalProject.dto.PurchaseDTO;
import com.finalProject.FinalProject.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public List<PurchaseDTO> getAll(){
        return purchaseService.getAll();
    }

    @PostMapping
    public PurchaseDTO addData(@RequestBody PurchaseDTO purchaseDTO){
        return purchaseService.addData(purchaseDTO);
    }

    @GetMapping("/{id}")
    public PurchaseDTO getByID(@PathVariable("id") Long id){
        return purchaseService.getById(id);
    }

    @PutMapping("/{id}")
    public PurchaseDTO putProduct(@PathVariable ("id") Long id, @RequestBody PurchaseDTO purchaseDTO){
        return purchaseService.updateData(id,purchaseDTO);
    }
}
