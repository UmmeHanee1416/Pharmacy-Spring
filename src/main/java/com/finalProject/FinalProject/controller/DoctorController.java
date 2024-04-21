package com.finalProject.FinalProject.controller;


import com.finalProject.FinalProject.dto.DoctorDTO;
import com.finalProject.FinalProject.dto.EmployeeDTO;
import com.finalProject.FinalProject.entity.Doctor;
import com.finalProject.FinalProject.entity.Employee;
import com.finalProject.FinalProject.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<DoctorDTO> getAll(){
        return doctorService.getAll();
    }

    @PostMapping
    public Doctor addData(@RequestBody DoctorDTO doctorDTO){
        return doctorService.addData(doctorDTO);
    }

    @GetMapping("/{id}")
    public DoctorDTO getByID(@PathVariable("id") Long id){
        return doctorService.getById(id);
    }


    @PutMapping("/{id}")
    public Doctor putProduct(@PathVariable ("id") Long id, @RequestBody DoctorDTO doctorDTO){
        return doctorService.updateData(id,doctorDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable("id") Long id){
        doctorService.deleteByID(id);
    }
}
