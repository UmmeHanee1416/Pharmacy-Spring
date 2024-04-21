package com.finalProject.FinalProject.service;

import com.finalProject.FinalProject.dto.EmployeeDTO;
import com.finalProject.FinalProject.dto.EmployeeDetailDTO;
import com.finalProject.FinalProject.entity.Employee;
import com.finalProject.FinalProject.entity.EmployeeDetail;
import com.finalProject.FinalProject.repository.EmployeeDetailRepo;
import com.finalProject.FinalProject.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeDetailRepo employeeDetailRepo;

    public List<EmployeeDTO> getAll(){
        List<Employee> employeeDetails = employeeRepo.findAll();
        List<EmployeeDTO> employeeDetailDTOS = new ArrayList<>();
        for (Employee e: employeeDetails) {
            EmployeeDTO employeeDetailDTO = new EmployeeDTO();
            maptoDTO(e,employeeDetailDTO);
            employeeDetailDTOS.add(employeeDetailDTO);
        }
        return employeeDetailDTOS;
    }

    public EmployeeDTO addData(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        maptoEntity(employeeDTO,employee);
        return maptoDTO(employeeRepo.save(employee), new EmployeeDTO());
    }


    public Employee updateData(Long id,EmployeeDTO employeeDetailDTO){
        Employee employeeDetail = new Employee();
        maptoEntity(employeeDetailDTO,employeeDetail);
        return employeeRepo.save(employeeDetail);
    }

    public EmployeeDTO getById(Long id){
        Employee employee = employeeRepo.findById(id).get();
        EmployeeDetail employeeDetail = employeeDetailRepo.findById(employee.getEmployeeDetail().getId()).get();
        EmployeeDetailDTO employeeDetailDTO = new EmployeeDetailDTO();
        maptoDTO(employeeDetail,employeeDetailDTO);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        maptoDTO(employee,employeeDTO);
        return employeeDTO;
    }

    public void deleteByID(Long id){
        EmployeeDTO employeeDTO = getById(id);
        employeeRepo.deleteById(id);
        employeeDetailRepo.deleteById(employeeDTO.getEmployeeDetail());
    }


    public Employee maptoEntity(EmployeeDTO employeeDTO,Employee employee){
        if (employee!=null){
            employee.setId(employeeDTO.getId());
        }
        employee.setName(employeeDTO.getName());
        employee.setExperience(employeeDTO.getExperience());
        employee.setEducation(employeeDTO.getEducation());
        employee.setEmployeeDetail(maptoEmployeeDetail(employeeDTO));
        employee.setReference(employeeDTO.getReference());
        return employee;
    }

    public EmployeeDTO maptoDTO(Employee employee,EmployeeDTO employeeDTO){
        if (employeeDTO!=null){
            employeeDTO.setId(employee.getId());
        }
        employeeDTO.setName(employee.getName());
        employeeDTO.setExperience(employee.getExperience());
        employeeDTO.setEducation(employee.getEducation());
        employeeDTO.setEmployeeDetail(maptoEmployeeDetailDTO(employee));
        employeeDTO.setReference(employee.getReference());
        return employeeDTO;
    }

    public EmployeeDetail maptoEmployeeDetail(EmployeeDTO employeeDTO){
        EmployeeDetail employeeDetail = employeeDetailRepo.findById(employeeDTO.getEmployeeDetail()).get();
        return employeeDetail;
    }

    public Long maptoEmployeeDetailDTO(Employee employee){
        Long l = employee.getEmployeeDetail().getId();
        return l;
    }

    public EmployeeDetailDTO maptoDTO(EmployeeDetail employeeDetail,EmployeeDetailDTO employeeDetailDTO){
        if (employeeDetailDTO!=null){
            employeeDetailDTO.setId(employeeDetail.getId());
        }
        employeeDetailDTO.setPermanentAdd(employeeDetail.getPermanentAdd());
        employeeDetailDTO.setCurrentAdd(employeeDetail.getCurrentAdd());
        employeeDetailDTO.setFamilyMembers(employeeDetail.getFamilyMembers());
        return employeeDetailDTO;
    }


}
