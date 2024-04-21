package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.entity.ReturnedProductShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnedProductShopRepo extends JpaRepository<ReturnedProductShop,Long> {
}
