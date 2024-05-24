package com.finalProject.FinalProject.service;

import com.finalProject.FinalProject.dto.*;
import com.finalProject.FinalProject.entity.*;
import com.finalProject.FinalProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private GenericRepo genericRepo;

    public CustomerDTO addData(CustomerDTO customerDTO){
        if(!customerRepo.existsById(customerDTO.getContact())){
            Customer customer = new Customer();
            maptoEntity(customerDTO,customer);
            customerRepo.save(customer);
            Sales sales  = new Sales();
            sales.setCustomerId(customer);
            sales.setEmpId(employeeRepo.findById(customerDTO.getEmpId()).get());
            maptoDTO(salesRepo.save(sales), new SalesDTO());
            int abc = 0;
            int a =  0;
            for (SalesDetailDTO s: customerDTO.getSalesDetailDTOS()) {
                abc += s.getProductQuantity();
                SalesDetail salesDetail = new SalesDetail();
                maptoEntity(s,salesDetail);
                Product product = productRepo.findById(salesDetail.getProductId().getTradeName()).get();
                a += (product.getSellPrice() * s.getProductQuantity()) ;
                productRepo.updateProduct((product.getRemainedQuantity() - s.getProductQuantity()), (product.getSoldQuantity()!=null?product.getSoldQuantity():0) + s.getProductQuantity(), product.getTradeName());
                Set<Generic> generics = product.getGenerics();
                for (Generic g: generics) {
                    genericRepo.updateGenericRegister((g.getRegisteredQuantity()!=null?g.getRegisteredQuantity():0)-(s.getProductQuantity()), g.getId());
                }

            }
            sales.setTotalQuantity(abc);
            sales.setTotalCharge(a);
            updateData(sales.getId(), sales);
            for (SalesDetailDTO s: customerDTO.getSalesDetailDTOS()) {
                SalesDetail salesDetail = new SalesDetail();
                salesDetail.setSalesId(sales);
                maptoDTO(salesDetailRepo.save(maptoEntity(s,salesDetail)), new SalesDetailDTO());
            }
            InvoiceDTO invoiceDTO = new InvoiceDTO();
            invoiceDTO.setSalesId(salesRepo.findById(sales.getId()).get().getId());
            invoiceDTO.setCustomerId(customerRepo.findById(customerDTO.getContact()).get().getContact());
            invoiceDTO.setSoldQty(abc);
            invoiceDTO.setSaleDate(sales.getCreatedAt());
            addData(invoiceDTO);
        } else {
            Sales sales  = new Sales();
            sales.setCustomerId(customerRepo.findById(customerDTO.getContact()).get());
            sales.setEmpId(employeeRepo.findById(customerDTO.getEmpId()).get());
            maptoDTO(salesRepo.save(sales), new SalesDTO());
            int abc = 0;
            int a =  0;
            for (SalesDetailDTO s: customerDTO.getSalesDetailDTOS()) {
                abc += s.getProductQuantity();
                SalesDetail salesDetail = new SalesDetail();
                maptoEntity(s,salesDetail);
                Product product = productRepo.findById(salesDetail.getProductId().getTradeName()).get();
                a += (product.getSellPrice() * s.getProductQuantity()) ;
                productRepo.updateProduct((product.getRemainedQuantity() - s.getProductQuantity()), (product.getSoldQuantity()!=null?product.getSoldQuantity():0) + s.getProductQuantity(), product.getTradeName());
                Set<Generic> generics = product.getGenerics();
                for (Generic g: generics) {
                    genericRepo.updateGenericRegister((g.getRegisteredQuantity()!=null?g.getRegisteredQuantity():0)-(s.getProductQuantity()), g.getId());
                }
                salesDetail.setSalesId(salesRepo.findById(sales.getId()).get());
                maptoDTO(salesDetailRepo.save(maptoEntity(s,salesDetail)), new SalesDetailDTO());
            }
            sales.setTotalQuantity(abc);
            sales.setTotalCharge(a);
            updateData(sales.getId(), sales);
            InvoiceDTO invoiceDTO = new InvoiceDTO();
            invoiceDTO.setSalesId(salesRepo.findById(sales.getId()).get().getId());
            invoiceDTO.setCustomerId(customerRepo.findById(customerDTO.getContact()).get().getContact());
            invoiceDTO.setSoldQty(abc);
            invoiceDTO.setSaleDate(customerRepo.findById(customerDTO.getContact()).get().getPurchaseDate());
            addData(invoiceDTO);
        }
        return null;
    }

    public List<CustomerDTO> getAll(){
        List<Customer> customers = customerRepo.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer c: customers) {
            CustomerDTO customerDTO = new CustomerDTO();
            maptoDTO(c,customerDTO);
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    public CustomerDTO getById(String id){
        Customer customer = customerRepo.findById(id).get();
        CustomerDTO customerDTO = new CustomerDTO();
        maptoDTO(customer,customerDTO);
        return customerDTO;
    }

    public Customer updateData(Long id,CustomerDTO customerDTO){
        Customer customer = new Customer();
        maptoEntity(customerDTO,customer);
        return customerRepo.save(customer);
    }

    public void deleteByID(String id){
        customerRepo.deleteById(id);
    }

    public Customer maptoEntity(CustomerDTO customerDTO,Customer customer){
        if (customer!=null){
            customer.setContact(customerDTO.getContact());
        }
        customer.setName(customerDTO.getName());
//        customer.setContact(customerDTO.getContact());
        customer.setPurchaseDate(customerDTO.getPurchaseDate());
        customer.setPayMethod(customerDTO.getPayMethod());
        customer.setEmpId(employeeRepo.findById(customerDTO.getEmpId()).get());
        return customer;
    }

    public CustomerDTO maptoDTO(Customer customer, CustomerDTO customerDTO){
        if (customerDTO!=null){
            customerDTO.setContact(customer.getContact());
        }
        customerDTO.setName(customer.getName());
//        customerDTO.setContact(customer.getContact());
        customerDTO.setPurchaseDate(customer.getPurchaseDate());
        customerDTO.setPayMethod(customer.getPayMethod());
        customerDTO.setEmpId(employeeRepo.findById(customer.getEmpId().getId()).get().getId());
        return customerDTO;
    }

//    -----sales service------

    @Autowired
    private SalesRepo salesRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<SalesDTO> getAllSales(){
        List<Sales> salesList = salesRepo.findAll();
        List<SalesDTO> salesDTOS = new ArrayList<>();
        for (Sales s: salesList) {
            System.out.println(s.getCreatedAt().getDayOfWeek());
            SalesDTO salesDTO = new SalesDTO();
            maptoDTO(s,salesDTO);
            salesDTOS.add(salesDTO);
        }
        return salesDTOS;
    }


    public SalesDTO addData(SalesDTO salesDTO){
        Sales sales = new Sales();
        maptoEntity(salesDTO,sales);
        return maptoDTO(salesRepo.save(sales), new SalesDTO());
    }

    public SalesDTO updateData(Long id, Sales sales){
//        Sales sales = new Sales();
//        maptoEntity(salesDTO,sales);
        return maptoDTO(salesRepo.save(sales), new SalesDTO());
    }

    public SalesDTO getById(Long id){
        Sales sales = salesRepo.findById(id).get();
//        Customer customer = sales.getCustomerId();
        SalesDTO salesDTO = new SalesDTO();
        maptoDTO(sales,salesDTO);
        return salesDTO;
    }

    public void deleteByID(Long id){
        salesRepo.deleteById(id);
    }

    public SalesDTO maptoDTO(Sales sales, SalesDTO salesDTO){
        if (salesDTO!=null){
            salesDTO.setId(sales.getId());
        }
        salesDTO.setCustomerId(sales.getCustomerId()!=null? sales.getCustomerId().getContact():"");
        salesDTO.setEmpId(sales.getEmpId()!=null? sales.getEmpId().getId():null);
        salesDTO.setEmpName(sales.getEmpId()!=null? sales.getEmpId().getName():null);
        salesDTO.setTotalQuantity(sales.getTotalQuantity());
        salesDTO.setTotalCharge(sales.getTotalCharge());
         return salesDTO;
    }

    public Sales maptoEntity(SalesDTO salesDTO, Sales sales){
        if (sales!=null){
            sales.setId(salesDTO.getId());
        }
//        sales.setCustomerId(customerRepo.findById(salesDTO.getCustomerId()).get());

        sales.setTotalQuantity(salesDTO.getTotalQuantity());
        sales.setTotalCharge(salesDTO.getTotalCharge());
        return sales;
    }


//    ----------salesdetail----------

    @Autowired
    private SalesDetailRepo salesDetailRepo;

    @Autowired
    private ProductRepo productRepo;

    public List<SalesDetailDTO> getAllsd(){
        List<SalesDetail> salesDetails = salesDetailRepo.findAll();
        List<SalesDetailDTO> salesDetailDTOS = new ArrayList<>();
        for (SalesDetail s: salesDetails) {
            SalesDetailDTO salesDetailDTO = new SalesDetailDTO();
            maptoDTO(s,salesDetailDTO);
            salesDetailDTOS.add(salesDetailDTO);
        }
        return salesDetailDTOS;
    }
    private List<SalesDetailDTO> salesDetails = new ArrayList<>();

    public SalesDetailDTO addData(SalesDetailDTO salesDetailDTO){
        SalesDetail salesDetail = new SalesDetail();
        maptoEntity(salesDetailDTO,salesDetail);
        salesDetails.add(salesDetailDTO);
        return salesDetailDTO;
    }

    public SalesDetail updateData(Long id,SalesDetailDTO salesDetailDTO){
        SalesDetail salesDetail = new SalesDetail();
        maptoEntity(salesDetailDTO,salesDetail);
        return salesDetailRepo.save(salesDetail);
    }

    public SalesDetailDTO getByIdsd(Long id){
        SalesDetail salesDetail = salesDetailRepo.findById(id).get();
        SalesDetailDTO salesDetailDTO = new SalesDetailDTO();
        maptoDTO(salesDetail,salesDetailDTO);
        return salesDetailDTO;
    }

    public List<SalesDetailDTO> getBySales(Long id){
        List<SalesDetail> salesDetails = salesDetailRepo.getAllByPurchaseId(salesRepo.findById(id).get().getId());
        List<SalesDetailDTO> salesDetailDTOS = new ArrayList<>();
        for (SalesDetail s: salesDetails) {
            SalesDetailDTO salesDetailDTO = new SalesDetailDTO();
            maptoDTO(s,salesDetailDTO);
            salesDetailDTOS.add(salesDetailDTO);
        }
        return salesDetailDTOS;
    }

    public void deleteByIDsd(Long id){
        salesDetailRepo.deleteById(id);
    }

    public SalesDetailDTO maptoDTO(SalesDetail salesDetail,SalesDetailDTO salesDetailDTO){
        if (salesDetailDTO!=null){
            salesDetailDTO.setId(salesDetail.getId());
        }
        salesDetailDTO.setSalesId(salesRepo.findById(salesDetail.getSalesId().getId()).get().getId());
        salesDetailDTO.setProductId(productRepo.findById(salesDetail.getProductId().getTradeName()).get().getTradeName());
        salesDetailDTO.setProductQuantity(salesDetail.getProductQuantity());
        salesDetailDTO.setTotalCharge(salesDetail.getTotalCharge());
        return salesDetailDTO;
    }

    public SalesDetail maptoEntity(SalesDetailDTO salesDetailDTO,SalesDetail salesDetail){
        if (salesDetail!=null){
            salesDetail.setId(salesDetailDTO.getId());
            salesDetail.setTotalCharge(salesDetailDTO.getTotalCharge());
        }
        salesDetail.setProductId(productRepo.findById(salesDetailDTO.getProductId()).get());
        salesDetail.setProductQuantity(salesDetailDTO.getProductQuantity());
        salesDetail.setTotalCharge(productRepo.findById(salesDetailDTO.getProductId()).get(),salesDetailDTO.getProductQuantity());
        return salesDetail;
    }

//    invoice service

    @Autowired
    private InvoiceRepo invoiceRepo;

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

    public InvoiceDTO getByIdInv(Long id){
        return maptoDTO(invoiceRepo.getBySalesId(id), new InvoiceDTO());
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
