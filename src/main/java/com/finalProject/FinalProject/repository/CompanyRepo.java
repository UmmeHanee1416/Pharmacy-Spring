package com.finalProject.FinalProject.repository;


import com.finalProject.FinalProject.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company,String> {
}
