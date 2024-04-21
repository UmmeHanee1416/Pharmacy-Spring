package com.finalProject.FinalProject.service;


import com.finalProject.FinalProject.dto.RoleDto;
import com.finalProject.FinalProject.entity.Role;
import com.finalProject.FinalProject.repository.RoleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDto> findAll() {
        final List<Role> Roles = roleRepository.findAll(Sort.by("roleName"));
        return Roles.stream()
                .map((Role) -> mapToDTO(Role, new RoleDto()))
                .toList();
    }

    public RoleDto get(final String roleName) {
        return roleRepository.findById(roleName)
                .map(Role -> mapToDTO(Role, new RoleDto()))
                .orElseThrow(RuntimeException::new);
    }

    public String create(final RoleDto RoleDto) {
        final Role Role = new Role();
        mapToEntity(RoleDto, Role);
        Role.setRoleName(RoleDto.getRoleName());
        return roleRepository.save(Role).getRoleName();
    }

    public void update(final String roleName, final RoleDto RoleDto) {
        final Role Role = roleRepository.findById(roleName)
                .orElseThrow(RuntimeException::new);
        mapToEntity(RoleDto, Role);
        roleRepository.save(Role);
    }

    public void delete(final String roleName) {
        roleRepository.deleteById(roleName);
    }

    private RoleDto mapToDTO(final Role Role, final RoleDto RoleDto) {
        RoleDto.setRoleName(Role.getRoleName());
        RoleDto.setRoleDescription(Role.getRoleDescription());
        return RoleDto;
    }

    private Role mapToEntity(final RoleDto RoleDto, final Role Role) {
        Role.setRoleDescription(RoleDto.getRoleDescription());
        return Role;
    }

    public boolean roleNameExists(final String roleName) {
        return roleRepository.existsByRoleNameIgnoreCase(roleName);
    }

}
