package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.entity.ReturnedtoCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnedtoCompanyRepo extends JpaRepository<ReturnedtoCompany,Long> {
}
