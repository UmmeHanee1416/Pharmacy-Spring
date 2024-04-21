package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    boolean existsByRoleNameIgnoreCase(String roleName);
}
