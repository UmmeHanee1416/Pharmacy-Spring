package com.finalProject.FinalProject.controller;

import com.finalProject.FinalProject.dto.CustomerDTO;
import com.finalProject.FinalProject.entity.Customer;
import com.finalProject.FinalProject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAll(){
        return customerService.getAll();
    }

    @PostMapping
    public CustomerDTO addData(@RequestBody CustomerDTO customerDTO){
        return customerService.addData(customerDTO);
    }

    @GetMapping("/{id}")
    public CustomerDTO getByID(@PathVariable("id") String id){
        return customerService.getById(id);
    }


    @PutMapping("/{id}")
    public Customer putProduct(@PathVariable ("id") Long id, @RequestBody CustomerDTO customerDTO){
        return customerService.updateData(id,customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable("id") String id){
        customerService.deleteByID(id);
    }
}
