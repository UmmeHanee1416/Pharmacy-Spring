package com.finalProject.FinalProject.service;

import com.finalProject.FinalProject.dto.ProductDTO;
import com.finalProject.FinalProject.entity.*;
import com.finalProject.FinalProject.repository.CompanyRepo;
import com.finalProject.FinalProject.repository.GenericRepo;
import com.finalProject.FinalProject.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private GenericRepo genericRepo;

    public List<ProductDTO> getAll(){
        List<Product> products = productRepo.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product p: products) {
            ProductDTO productDTO = new ProductDTO();
            maptoDTO(p,productDTO);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    public ProductDTO addData(ProductDTO productDTO){
        Product product = new Product();
        maptoEntity(productDTO,product);
        product.setRemainedQuantity(productDTO.getRegsrtQuantity() - (productDTO.getSoldQuantity()!=null?productDTO.getSoldQuantity():0));
        ProductDTO productDTO1 = new ProductDTO();
        maptoDTO(productRepo.save(product),productDTO1);
        Set<Generic> generics = product.getGenerics();
        for (Generic g: generics) {
            genericRepo.updateGenericRegister((g.getRegisteredQuantity()!=null?g.getRegisteredQuantity():0)+product.getRemainedQuantity(), g.getId());
        }
        return productDTO1;
    }


    public ProductDTO updateData(String id,ProductDTO productDTO){
        Product product = new Product();
        product.setRemainedQuantity(productDTO.getRegsrtQuantity() - (productDTO.getSoldQuantity()!=null?productDTO.getSoldQuantity():0));
        maptoEntity(productDTO,product);
        return maptoDTO(productRepo.save(product), new ProductDTO());
    }

    public ProductDTO getById(String id){
        Product product = productRepo.findById(id).get();
        ProductDTO productDTO = new ProductDTO();
        maptoDTO(product,productDTO);
        return productDTO;
    }

    public void deleteByID(String id){
        productRepo.deleteById(id);
    }

    public Company maptoCompany(ProductDTO productDTO){
        Company company = companyRepo.findById(productDTO.getCompanyName()).get();
        return company;
    }

    public String maptoCompanyName(Product product){
        String productDTO = product.getCompanyName().getName();
        return productDTO;
    }

    public Product maptoEntity(ProductDTO productDTO,Product product){
        if (product!=null){
            product.setTradeName(productDTO.getTradeName());
        }
        product.setTradeName(productDTO.getTradeName());
        product.setCompanyName(maptoCompany(productDTO));
        product.setRegsrtQuantity(productDTO.getRegsrtQuantity());
        product.setSoldQuantity(productDTO.getSoldQuantity());
        product.setRemainedQuantity(productDTO.getRemainedQuantity());
        product.setMfd(productDTO.getMfd());
        product.setExp(productDTO.getExp());
        product.setSellPrice(productDTO.getSellPrice());
        final List<Generic> generics = genericRepo.findAllById(productDTO.getGenerics() == null ? Collections.emptyList() : productDTO.getGenerics());
        if (generics.size() != (productDTO.getGenerics() == null ? 0 : productDTO.getGenerics().size())) {
            throw new RuntimeException("one of generics not found");
        }
        product.setGenerics(new HashSet<>(generics));
        return product;
    }

    public ProductDTO maptoDTO(Product product,ProductDTO productDTO){
        if (productDTO!=null){
            productDTO.setTradeName(product.getTradeName());
        }
//        productDTO.setTradeName(product.getTradeName());
        productDTO.setCompanyName(maptoCompanyName(product));
        productDTO.setRegsrtQuantity(product.getRegsrtQuantity());
        productDTO.setSoldQuantity(product.getSoldQuantity()!=null?product.getSoldQuantity():0);
        productDTO.setRemainedQuantity(product.getRemainedQuantity());
        productDTO.setMfd(product.getMfd());
        productDTO.setExp(product.getExp());
        productDTO.setSellPrice(product.getSellPrice());
        productDTO .setGenerics(product.getGenerics() == null ? null : product.getGenerics().stream()
                .map(Generic::getId)
                .collect(Collectors.toSet()));
        return productDTO;
    }

    public List<Object[]> getallTradeNameAndRemainedQuantity() {
       return productRepo.getTradeNameAndRemainedQuanity();
    }
}
