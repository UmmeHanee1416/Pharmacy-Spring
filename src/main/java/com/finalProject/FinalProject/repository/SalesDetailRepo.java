package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.dto.SalesDTO;
import com.finalProject.FinalProject.dto.SalesDetailDTO;
import com.finalProject.FinalProject.entity.Sales;
import com.finalProject.FinalProject.entity.SalesDetail;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Repository
public interface SalesDetailRepo extends JpaRepository<SalesDetail,Long> {

    @Query(value = " select * from sales_detail where sales_id = :sId ", nativeQuery = true)
    List<SalesDetail> getAllByPurchaseId(@Param("sId") Long id);

    @Query(value = " select * from sales_detail where product_id = :pId ", nativeQuery = true)
    List<SalesDetailDTO> getAllByProductId(@Param("pId") String tradeName);

    @Query(value = "  select s.customer_id from sales s join sales_detail sd on sd.sales_id = s.id where sd.product_id = :pId", nativeQuery = true)
    List<SalesDetailDTO> getAllCustomer(@Param("pId") String tradeName);

    @Query(value = " SELECT SUM(total_charge) FROM sales WHERE DATE(created_at) = :date ", nativeQuery = true)
    Integer getTotalChargeWed(@Param("date") String date);

    @Query(value = " SELECT pharmacy.sales_detail.id, pharmacy.sales_detail.product_quantity,pharmacy.sales_detail.total_charge,pharmacy.sales_detail.product_id,pharmacy.sales_detail.sales_id,pharmacy.sales.id,pharmacy.sales.total_charge,pharmacy.sales.total_quantity,pharmacy.sales.customer_id FROM pharmacy.sales_detail join pharmacy.sales ON  pharmacy.sales_detail.sales_id = pharmacy.sales.id where  pharmacy.sales_detail.sales_id = :salesid ", nativeQuery = true)
    Collection<?> getInvoice(@Param("salesid") Integer a);
}
