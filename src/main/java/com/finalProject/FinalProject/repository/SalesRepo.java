package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface SalesRepo extends JpaRepository<Sales,Long> {

    @Query(value = " SELECT sd.product_quantity FROM sales_detail sd JOIN sales s ON s.id = sd.purchase_id WHERE s.id  =  :id ", nativeQuery = true)
    List<Integer> getAllProductQty(@Param("id") Long id);

    @Query(value = " SELECT sd.total_charge FROM sales_detail sd JOIN sales s ON s.id = sd.purchase_id WHERE s.id  =  :id ", nativeQuery = true)
    List<Integer> getAllProductChrg(@Param("id") Long id);


    @Query(value = "UPDATE sales s SET s.total_quantity = :qt WHERE s.id = :id "
            + "AND s.id IN (SELECT purchase_id FROM sales_detail WHERE purchase_id = :id)", nativeQuery = true)
    void updateqty(@Param("qt") int abc, @Param("id") Long salesId);


//    @Query(value = " select trade_name, remained_quantity from product ", nativeQuery = true)
//    List<Object[]> getTradeNameAndRemainedQuanity();

    @Query(value = " SELECT total_charge FROM sales WHERE DAYNAME(created_at) = :wednesday ", nativeQuery = true)
    List<Integer> findAllCharge(@Param("wednesday") String date);

}
