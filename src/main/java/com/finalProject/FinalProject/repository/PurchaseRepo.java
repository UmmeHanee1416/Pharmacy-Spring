package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Long> {
}
