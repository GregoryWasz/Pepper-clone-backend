package com.pepper.backend.service;

import com.pepper.backend.exception.ResourceNotFoundException;
import com.pepper.backend.model.Role;
import com.pepper.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role addRole(Role role){
        return roleRepository.save(role);
    }

    public Role findRoleById(int id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not exist with id: " + id));
    }
}
