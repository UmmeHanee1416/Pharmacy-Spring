package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.entity.Invoice;
import com.finalProject.FinalProject.service.RoleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
    @Query(value = " SELECT * FROM `invoice` WHERE `sales_id` = :sId ", nativeQuery = true)
    Invoice getBySalesId(@Param("sId") Long id);
}
