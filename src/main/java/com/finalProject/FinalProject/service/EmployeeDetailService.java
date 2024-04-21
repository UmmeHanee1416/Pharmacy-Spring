package com.finalProject.FinalProject.service;

import com.finalProject.FinalProject.dto.EmployeeDetailDTO;
import com.finalProject.FinalProject.entity.EmployeeDetail;
import com.finalProject.FinalProject.repository.EmployeeDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeDetailService {

    @Autowired
    private EmployeeDetailRepo employeeDetailRepo;

    public List<EmployeeDetailDTO> getAll(){
        List<EmployeeDetail> employeeDetails = employeeDetailRepo.findAll();
        List<EmployeeDetailDTO> employeeDetailDTOS = new ArrayList<>();
        for (EmployeeDetail e: employeeDetails) {
            EmployeeDetailDTO employeeDetailDTO = new EmployeeDetailDTO();
            maptoDTO(e,employeeDetailDTO);
            employeeDetailDTOS.add(employeeDetailDTO);
        }
        return employeeDetailDTOS;
    }

    public EmployeeDetail addData(EmployeeDetailDTO employeeDetailDTO){
        EmployeeDetail employeeDetail = new EmployeeDetail();
        maptoEntity(employeeDetailDTO,employeeDetail);
        return employeeDetailRepo.save(employeeDetail);
    }


    public EmployeeDetail updateData(Long id,EmployeeDetailDTO employeeDetailDTO){
        EmployeeDetail employeeDetail = new EmployeeDetail();
        maptoEntity(employeeDetailDTO,employeeDetail);
        return employeeDetailRepo.save(employeeDetail);
    }

    public EmployeeDetailDTO getById(Long id){
        EmployeeDetail employeeDetail = employeeDetailRepo.findById(id).get();
        EmployeeDetailDTO employeeDetailDTO = new EmployeeDetailDTO();
        maptoDTO(employeeDetail,employeeDetailDTO);
        return employeeDetailDTO;
    }

    public void deleteByID(Long id){
        employeeDetailRepo.deleteById(id);
    }


    public EmployeeDetail maptoEntity(EmployeeDetailDTO employeeDetailDTO,EmployeeDetail employeeDetail){
        if (employeeDetail!=null){
            employeeDetail.setId(employeeDetailDTO.getId());
        }
        employeeDetail.setPermanentAdd(employeeDetailDTO.getPermanentAdd());
        employeeDetail.setCurrentAdd(employeeDetailDTO.getCurrentAdd());
        employeeDetail.setFamilyMembers(employeeDetailDTO.getFamilyMembers());
        return employeeDetail;
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
