package com.finalProject.FinalProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "FILE_DATA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String filePath;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fileData", referencedColumnName = "id")
    @JsonIgnore
    private Set<ReturnedProductShop> returnedProductShops;
}
