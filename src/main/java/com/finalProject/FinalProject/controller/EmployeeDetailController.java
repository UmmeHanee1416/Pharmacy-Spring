package com.finalProject.FinalProject.controller;


import com.finalProject.FinalProject.dto.EmployeeDetailDTO;
import com.finalProject.FinalProject.entity.EmployeeDetail;
import com.finalProject.FinalProject.service.EmployeeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empdet")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class EmployeeDetailController {

    @Autowired
    private EmployeeDetailService employeeDetailService;

    @GetMapping
    public List<EmployeeDetailDTO> getAll(){
        return employeeDetailService.getAll();
    }

    @PostMapping
    public EmployeeDetail addData(@RequestBody EmployeeDetailDTO employeeDetailDTO){
        return employeeDetailService.addData(employeeDetailDTO);
    }

    @GetMapping("/{id}")
    public EmployeeDetailDTO getByID(@PathVariable("id") Long id){
        return employeeDetailService.getById(id);
    }


    @PutMapping("/{id}")
    public EmployeeDetail putProduct(@PathVariable ("id") Long id, @RequestBody EmployeeDetailDTO employeeDetailDTO){
        return employeeDetailService.updateData(id,employeeDetailDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable("id") Long id){
        employeeDetailService.deleteByID(id);
    }
}
