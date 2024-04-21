package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long> {
}
