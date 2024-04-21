package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileDataRepository extends JpaRepository<FileData,Long> {
    List<FileData> findByName(String fileName);

    @Query(value = "SELECT * FROM FILE_DATA where name = :name limit 1", nativeQuery = true)
    public FileData findAllSortedByNameUsingNative(@Param(value = "name") String name );
}
