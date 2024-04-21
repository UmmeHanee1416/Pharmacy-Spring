package com.finalProject.FinalProject.repository;


import com.finalProject.FinalProject.entity.Generic;
import com.finalProject.FinalProject.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {

    @Query(value = " select trade_name, remained_quantity from product ", nativeQuery = true)
    List<Object[]> getTradeNameAndRemainedQuanity();

    @Transactional
    @Query(value = "UPDATE product SET remained_quantity = :qty, sold_quantity = :s_qty WHERE trade_name = :product_id", nativeQuery = true)
    @Modifying
    void updateProduct(@Param("qty") Integer name, @Param("s_qty") Integer phone, @Param("product_id") String id);

    @Transactional
    @Query(value = "UPDATE product SET remained_quantity = :qty WHERE trade_name = :product_id", nativeQuery = true)
    @Modifying
    void updateRemainQTY(@Param("qty") Integer name, @Param("product_id") String id);

    @Transactional
    @Query(value = "UPDATE product SET regsrt_quantity = :qty, remained_quantity = :rqty WHERE trade_name = :product_id", nativeQuery = true)
    @Modifying
    void updateProductRegisterAndRemainQTY(@Param("qty") Integer qty, @Param("rqty") Integer rqty, @Param("product_id") String id);

    @Query(value = " UPDATE g set g.registered_quantity = (select sum(p.remained_quantity) FROM generic_products gp INNER JOIN generic g ON gp.product_name = g.id INNER JOIN product p ON gp.generic_id = p.trade_name WHERE g.id=:g_id) ", nativeQuery = true)
    Integer getAllProductRemainByGenericId(@Param("g_id") Long gId);

    @Query(value = " SELECT g.* FROM generic_products gp INNER JOIN generic g ON gp.product_name = g.id INNER JOIN product p ON gp.generic_id = p.trade_name WHERE p.trade_name=:pName ", nativeQuery = true)
    List<Generic> getAllGeneric(@Param("pName") String pId);
}
