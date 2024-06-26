package com.wiktormalyska.backend.services;

import com.wiktormalyska.backend.dao.IRoleRepository;
import com.wiktormalyska.backend.model.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final IRoleRepository roleRepository;

    @Autowired
    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void addRole(String roleName) {
        Role roleTest = roleRepository.findByName(roleName).orElse(null);
        if (roleTest != null) {
            throw new IllegalArgumentException("Role already exists");
        }

        Role role = new Role();
        role.setName(roleName);
        roleRepository.save(role);
    }

    @Transactional
    public void removeRole(String roleName) {
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new IllegalArgumentException("Role not found"));
        roleRepository.delete(role);
    }

    @Transactional
    public Role getRole(String roleName) {
        return roleRepository.findByName(roleName).orElseThrow(() -> new IllegalArgumentException("Role not found"));
    }
}
