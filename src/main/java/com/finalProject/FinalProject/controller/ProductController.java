package com.finalProject.FinalProject.controller;


import com.finalProject.FinalProject.dto.ProductDTO;
import com.finalProject.FinalProject.entity.Product;
import com.finalProject.FinalProject.service.ProductService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAll(){
        return productService.getAll();
    }

    @PostMapping
    public ProductDTO addData(@RequestBody ProductDTO productDTO){
        return productService.addData(productDTO);
    }

    @GetMapping("/{id}")
    public ProductDTO getByID(@PathVariable("id") String id){
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public ProductDTO putProduct(@PathVariable ("id") String id, @RequestBody ProductDTO productDTO){
        return productService.updateData(id,productDTO);
    }

    @GetMapping("/tnameremQ")
    public List<Object[]> getTradeNameAndRemainedQuantity(){
    return productService.getallTradeNameAndRemainedQuantity();
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable("id") String id){
        productService.deleteByID(id);
    }


}
