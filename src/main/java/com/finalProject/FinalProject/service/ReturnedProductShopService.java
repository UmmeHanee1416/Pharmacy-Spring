package com.finalProject.FinalProject.service;

import com.finalProject.FinalProject.dto.ProductDTO;
import com.finalProject.FinalProject.dto.ReturnedProducShoptDTO;
import com.finalProject.FinalProject.dto.SalesDetailDTO;
import com.finalProject.FinalProject.entity.Generic;
import com.finalProject.FinalProject.entity.Product;
import com.finalProject.FinalProject.entity.ReturnedProductShop;
import com.finalProject.FinalProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ReturnedProductShopService {

    @Autowired
    private ReturnedProductShopRepo returnedProductShopRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private SalesDetailService salesDetailService;

    @Autowired
    private SalesDetailRepo salesDetailRepo;

    @Autowired
    private StorageRepository repository;

    @Autowired
    private FileDataRepository fileDataRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private GenericRepo genericRepo;

    @Value("${file.path}")
    private String FOLDER_PATH;

    public List<ReturnedProducShoptDTO> getAll(){
        List<ReturnedProductShop> returnedProductShops = returnedProductShopRepo.findAll();
        List<ReturnedProducShoptDTO> returnedProducShoptDTOS = new ArrayList<>();
        for (ReturnedProductShop r: returnedProductShops) {
            ReturnedProducShoptDTO returnedProducShoptDTO = new ReturnedProducShoptDTO();
            maptoDTO(r,returnedProducShoptDTO);
            returnedProducShoptDTOS.add(returnedProducShoptDTO);
        }
        return returnedProducShoptDTOS;
    }

    public ReturnedProducShoptDTO addData(ReturnedProducShoptDTO returnedProducShoptDTO){
        ReturnedProductShop returnedProductShop = new ReturnedProductShop();
        maptoEntity(returnedProducShoptDTO,returnedProductShop);
        Product product = productRepo.findById(returnedProducShoptDTO.getTradeName()).get();
        product.setRemainedQuantity(product.getRemainedQuantity()+ returnedProducShoptDTO.getReturnQTY());
        productRepo.updateProduct((product.getRemainedQuantity()+ returnedProducShoptDTO.getReturnQTY()), product.getSoldQuantity() - returnedProducShoptDTO.getReturnQTY(), product.getTradeName());
        Set<Generic> generics = product.getGenerics();
        for (Generic g: generics) {
            genericRepo.updateGenericRegister((g.getRegisteredQuantity()!=null?g.getRegisteredQuantity():0)+(returnedProducShoptDTO.getReturnQTY()), g.getId());
        }
        return maptoDTO(returnedProductShopRepo.save(returnedProductShop), new ReturnedProducShoptDTO());
    }


    public ReturnedProductShop updateData(Long id,ReturnedProducShoptDTO returnedProducShoptDTO){
        ReturnedProductShop returnedProductShop = new ReturnedProductShop();
        maptoEntity(returnedProducShoptDTO,returnedProductShop);
        return returnedProductShopRepo.save(returnedProductShop);
    }

    public ReturnedProducShoptDTO getById(Long id){
        ReturnedProductShop returnedProductShop = returnedProductShopRepo.findById(id).get();
        ReturnedProducShoptDTO returnedProducShoptDTO = new ReturnedProducShoptDTO();
        maptoDTO(returnedProductShop,returnedProducShoptDTO);
        return returnedProducShoptDTO;
    }

    public void deleteByID(Long id){
        returnedProductShopRepo.deleteById(id);
    }


    public ReturnedProductShop maptoEntity(ReturnedProducShoptDTO returnedProducShoptDTO,ReturnedProductShop returnedProductShop){
        if (returnedProductShop!=null){
            returnedProductShop.setId(returnedProducShoptDTO.getId());
        }
        returnedProductShop.setTradeName(productRepo.findById(returnedProducShoptDTO.getTradeName()).get());
        returnedProductShop.setCustomer(customerRepo.findById(returnedProducShoptDTO.getCustomer()).get());
        returnedProductShop.setPurchaseDate(returnedProducShoptDTO.getPurchaseDate());
        returnedProductShop.setReturnDate(returnedProducShoptDTO.getReturnDate());
        returnedProductShop.setReturnQTY(returnedProducShoptDTO.getReturnQTY());
        returnedProductShop.setFileData(storageService.downloadImageByID(returnedProducShoptDTO.getFileData()));
        return returnedProductShop;
    }

    public ReturnedProducShoptDTO maptoDTO(ReturnedProductShop returnedProductShop,ReturnedProducShoptDTO returnedProducShoptDTO){
        if (returnedProducShoptDTO!=null){
            returnedProducShoptDTO.setId(returnedProductShop.getId());
        }
        returnedProducShoptDTO.setTradeName(returnedProductShop.getTradeName().getTradeName());
        returnedProducShoptDTO.setCustomer(returnedProductShop.getCustomer().getContact());
        returnedProducShoptDTO.setPurchaseDate(returnedProductShop.getPurchaseDate());
        returnedProducShoptDTO.setReturnDate(returnedProductShop.getReturnDate());
        returnedProducShoptDTO.setReturnQTY(returnedProductShop.getReturnQTY());
        returnedProducShoptDTO.setFileData(returnedProductShop.getFileData().getId());
        return returnedProducShoptDTO;
    }


}
