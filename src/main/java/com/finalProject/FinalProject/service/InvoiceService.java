package com.finalProject.FinalProject.service;

import com.finalProject.FinalProject.dto.InvoiceDTO;
import com.finalProject.FinalProject.entity.Invoice;
import com.finalProject.FinalProject.repository.CustomerRepo;
import com.finalProject.FinalProject.repository.InvoiceRepo;
import com.finalProject.FinalProject.repository.SalesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepo invoiceRepo;

    @Autowired
    private SalesRepo salesRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public List<InvoiceDTO> getAllInv(){
        List<Invoice> salesDetails = invoiceRepo.findAll();
        List<InvoiceDTO> salesDetailDTOS = new ArrayList<>();
        for (Invoice s: salesDetails) {
            InvoiceDTO salesDetailDTO = new InvoiceDTO();
            maptoDTO(s,salesDetailDTO);
            salesDetailDTOS.add(salesDetailDTO);
        }
        return salesDetailDTOS;
    }


    public InvoiceDTO addData(InvoiceDTO invoiceDTO){
        Invoice invoice1 = new Invoice();
        maptoEntity(invoiceDTO,invoice1);
        return maptoDTO(invoiceRepo.save(invoice1), new InvoiceDTO());
    }

    public Invoice updateData(Long id, Invoice invoice){
        return invoiceRepo.save(invoice);
    }

    public Invoice getByIdInv(Long id){
        return invoiceRepo.findById(id).get();
    }

    public void deleteByIDInv(Long id){
        invoiceRepo.deleteById(id);
    }

    public InvoiceDTO maptoDTO(Invoice invoice, InvoiceDTO invoiceDTO){
        if (invoiceDTO!=null){
            invoiceDTO.setId(invoice.getId());
        }
        invoiceDTO.setSalesId(salesRepo.findById(invoice.getSalesId().getId()).get().getId());
        invoiceDTO.setCustomerId(customerRepo.findById(invoice.getCustomerId().getContact()).get().getContact());
        invoiceDTO.setSoldQty(invoice.getSoldQty());
        return invoiceDTO;
    }

    public Invoice maptoEntity(InvoiceDTO invoiceDTO,Invoice invoice){
        if (invoice!=null){
            invoice.setId(invoiceDTO.getId());
        }
        invoice.setSalesId(salesRepo.findById(invoiceDTO.getSalesId()).get());
        invoice.setCustomerId(customerRepo.findById(invoiceDTO.getCustomerId()).get());
        invoice.setSoldQty(invoiceDTO.getSoldQty());
        return invoice;
    }

}
