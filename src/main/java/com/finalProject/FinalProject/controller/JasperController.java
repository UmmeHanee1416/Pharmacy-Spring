package com.finalProject.FinalProject.controller;

import com.finalProject.FinalProject.repository.ProductRepo;
import com.finalProject.FinalProject.repository.SalesDetailRepo;
import com.finalProject.FinalProject.service.ProductService;
import com.finalProject.FinalProject.service.SalesDetailService;
import com.finalProject.FinalProject.service.SalesService;
import jakarta.persistence.criteria.CriteriaBuilder;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class JasperController {

    @Autowired
    private ProductService productRepo;

    @Autowired
    private SalesDetailService salesDetailService;

    @Autowired
    private SalesDetailRepo salesDetailRepo;

    @Autowired
    private SalesService salesService;

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadInvoice() throws JRException, IOException {

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                productRepo.getAll()


        );

        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("total", "7000");

        JasperReport compileReport = JasperCompileManager
                .compileReport(new FileInputStream("src/main/resources/Product.jrxml"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

        // JasperExportManager.exportReportToPdfFile(jasperPrint,
        // System.currentTimeMillis() + ".pdf");

        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

        System.err.println(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }

    @GetMapping(value = "/pInvoice", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadProductInvoice() throws JRException, IOException {

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                salesDetailService.getAll()
//                salesDetailRepo.getInvoice(a)
        );

        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("total", "7000");

        JasperReport compileReport = JasperCompileManager
                .compileReport(new FileInputStream("src/main/resources/ProductInvoice.jrxml"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

        // JasperExportManager.exportReportToPdfFile(jasperPrint,
        // System.currentTimeMillis() + ".pdf");

        byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);

        System.err.println(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }
}
