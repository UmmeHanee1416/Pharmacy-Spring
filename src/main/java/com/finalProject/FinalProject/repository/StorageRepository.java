package com.finalProject.FinalProject.repository;


import com.finalProject.FinalProject.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {
    Optional<ImageData> findByName(String fileName);
}
