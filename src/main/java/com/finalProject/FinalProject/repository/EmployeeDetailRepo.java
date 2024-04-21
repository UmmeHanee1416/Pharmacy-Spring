package com.finalProject.FinalProject.repository;


import com.finalProject.FinalProject.entity.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailRepo extends JpaRepository<EmployeeDetail,Long> {
}
