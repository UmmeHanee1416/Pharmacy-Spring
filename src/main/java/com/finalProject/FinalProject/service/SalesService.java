package com.finalProject.FinalProject.service;


import com.finalProject.FinalProject.dto.SalesDTO;
import com.finalProject.FinalProject.dto.SalesDetailDTO;
import com.finalProject.FinalProject.entity.Customer;
import com.finalProject.FinalProject.entity.Employee;
import com.finalProject.FinalProject.entity.Sales;
import com.finalProject.FinalProject.entity.SalesDetail;
import com.finalProject.FinalProject.repository.CustomerRepo;
import com.finalProject.FinalProject.repository.EmployeeRepo;
import com.finalProject.FinalProject.repository.SalesDetailRepo;
import com.finalProject.FinalProject.repository.SalesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Service
public class SalesService {

    @Autowired
    private SalesRepo salesRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private SalesDetailRepo salesDetailRepo;

    public List<SalesDTO> getAll(){
        List<Sales> Saless = salesRepo.findAll();
        List<SalesDTO> SalesDTOS = new ArrayList<>();
        for (Sales s: Saless) {
            SalesDTO SalesDTO = new SalesDTO();
//            maptoDTO(s,SalesDTO);
            SalesDTOS.add(SalesDTO);
        }
        return SalesDTOS;
    }


    public Sales addData(Customer customer, SalesDTO salesDTO, Sales sales){
        Sales Sales = new Sales();
        maptoEntity(customer,salesDTO,sales);
        return salesRepo.save(Sales);
    }

    public Sales updateData(Long id,Customer customer, SalesDTO salesDTO, Sales sales){
        Sales Sales = new Sales();
        maptoEntity(customer,salesDTO,sales);
        return salesRepo.save(Sales);
    }

    public SalesDTO getById(Long id){
        Sales sales = salesRepo.findById(id).get();
        Customer customer = sales.getCustomerId();
        SalesDTO salesDTO = new SalesDTO();
        maptoDTO(customer,salesDTO,sales);
        return salesDTO;
    }

    public void deleteByID(Long id){
        salesRepo.deleteById(id);
    }

    public SalesDTO maptoDTO(Customer customer, SalesDTO salesDTO, Sales sales){
        if (salesDTO!=null){
            salesDTO.setId(sales.getId());
        }
        salesDTO.setCustomerId(maptocustomerID(customer));
//        salesDTO.setTotalQuantity(sales.getTotalQuantity());
        salesDTO.setTotalCharge(sales.getTotalCharge());
        salesDTO.setEmpId(sales.getEmpId().getId());
        salesDTO.setEmpId(maptoemployeeID(sales));
        return salesDTO;
    }

    public Sales maptoEntity(Customer customer, SalesDTO salesDTO, Sales sales){
        if (sales!=null){
            sales.setId(salesDTO.getId());
        }
        sales.setCustomerId(maptocustomer(customer));
        sales.setEmpId(maptoEmployee(salesDTO));
        return sales;
    }

//    public Sales maptoSales(SalesDTO SalesDTO){
//        Sales sales = salesRepo.findById(SalesDTO.getPurchaseId()).get();
//        return sales;
//    }

    public Customer maptocustomer(Customer customer){
        Customer customer1 = customerRepo.findById(customer.getContact()).get();
        return customer1;
    }

    public Employee maptoEmployee(SalesDTO salesDTO){
        Employee customer = employeeRepo.findById(salesDTO.getEmpId()).get();
        return customer;
    }

    public String  maptocustomerID(Customer customer){
        String l = customer.getContact();
        return l;
    }

    public Long maptoemployeeID(Sales Sales){
        Long s = Sales.getEmpId().getId();
        return s;
    }

    public Integer dayOfWeek(String weekDay){
        List<Integer> list = salesRepo.findAllCharge(weekDay);
        Integer abc = 0;
        for (Integer i: list) {
            abc+=i;
        }
        System.out.println(abc);
        return abc;
    }
}
