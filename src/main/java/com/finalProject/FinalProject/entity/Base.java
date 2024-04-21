package com.finalProject.FinalProject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;


@MappedSuperclass
@Getter
@Setter
public abstract class Base {

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
    private Long createdBy;
    private Long updatedBy;
    private Boolean isActive;
    private Boolean deleted;


}
