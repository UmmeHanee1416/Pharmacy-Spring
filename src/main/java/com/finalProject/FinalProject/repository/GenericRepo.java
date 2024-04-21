package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.entity.Generic;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericRepo extends JpaRepository<Generic, Long> {

    @Transactional
    @Query(value = "UPDATE generic SET registered_quantity = :qty WHERE id = :generic_id", nativeQuery = true)
    @Modifying
    void updateGenericRegister(@Param("qty") Integer name, @Param("generic_id") Long id);
}
