package com.finalProject.FinalProject.controller;


import com.finalProject.FinalProject.dto.EmployeeDTO;
import com.finalProject.FinalProject.entity.Employee;
import com.finalProject.FinalProject.service.EmployeeDetailService;
import com.finalProject.FinalProject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDetailService employeeDetailService;

    @GetMapping
    public List<EmployeeDTO> getAll(){
        return employeeService.getAll();
    }

    @PostMapping
    public EmployeeDTO addData(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.addData(employeeDTO);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getByID(@PathVariable("id") Long id){
        return employeeService.getById(id);
    }


    @PutMapping("/{id}")
    public Employee putProduct(@PathVariable ("id") Long id, @RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateData(id,employeeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable("id") Long id){
        employeeService.deleteByID(id);
    }
}
