package com.finalProject.FinalProject.service;

import com.finalProject.FinalProject.dto.CompanyDTO;
import com.finalProject.FinalProject.dto.SalesDTO;
import com.finalProject.FinalProject.entity.Company;
import com.finalProject.FinalProject.entity.Sales;
import com.finalProject.FinalProject.repository.CompanyRepo;
import com.finalProject.FinalProject.repository.DoctorRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    public List<CompanyDTO> getAll(){
        List<Company> companies = companyRepo.findAll();
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        for (Company c: companies) {
            CompanyDTO companyDTO = new CompanyDTO();
            maptoDTO(c,companyDTO);
            companyDTOS.add(companyDTO);
        }
        return companyDTOS;
    }

    public CompanyDTO addData(CompanyDTO companyDTO){
        Company company = new Company();
        maptoEntity(companyDTO,company);
        return maptoDTO(companyRepo.save(company), new CompanyDTO());
    }

    public CompanyDTO updateData(Long id,CompanyDTO companyDTO){
        Company company = new Company();
        maptoEntity(companyDTO,company);
        return maptoDTO(companyRepo.save(company), new CompanyDTO());
    }

    public CompanyDTO getById(String id){
        Company company = companyRepo.findById(id).get();
        CompanyDTO companyDTO = new CompanyDTO();
        maptoDTO(company,companyDTO);
        return companyDTO;
    }

    public void deleteByID(String id){
        companyRepo.deleteById(id);
    }

    public CompanyDTO maptoDTO(Company sales, CompanyDTO salesDTO){

        salesDTO.setName(sales.getName()!=null? sales.getName():"");
        salesDTO.setDoctorID(doctorRepo.findById(sales.getDoctorID().getId()).get().getId());
        salesDTO.setRepresentativeName(sales.getRepresentativeName());
        salesDTO.setRepresentativeContact(sales.getRepresentativeContact());
        salesDTO.setSupplyAddress(sales.getSupplyAddress());
        return salesDTO;
    }

    public Company maptoEntity(CompanyDTO salesDTO, Company sales){

        sales.setName(salesDTO.getName());
        sales.setDoctorID(doctorRepo.findById(salesDTO.getDoctorID()).get());
        sales.setRepresentativeName(salesDTO.getRepresentativeName());
        sales.setRepresentativeContact(salesDTO.getRepresentativeContact());
        sales.setSupplyAddress(salesDTO.getSupplyAddress());
        return sales;
    }
}
