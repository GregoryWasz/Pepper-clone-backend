package com.pepper.backend.service;

import com.pepper.backend.exception.AlreadyExistException;
import com.pepper.backend.exception.ResourceNotFoundException;
import com.pepper.backend.model.Role;
import com.pepper.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    private void RoleNameValidation(Role role){
        String roleName = role.getRoleName().toUpperCase();
        role.setRoleName(roleName);
        boolean roleMatcher = roleName.matches("ROLE_[A-Z]+");
        if(!roleMatcher){
            String rolePrefix = "ROLE_";
            String roleNameWithPrefix = rolePrefix.concat(roleName);
            role.setRoleName(roleNameWithPrefix);
        }
        boolean matchFound = roleRepository.findAll().stream()
                .anyMatch(foundedRole -> foundedRole.getRoleName().equals(role.getRoleName()));
        if(matchFound){
            throw new AlreadyExistException("Role with that name already exist");
        }
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role addRole(Role role) {
        RoleNameValidation(role);
        return roleRepository.save(role);
    }

    public Role findRoleById(long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not exist with id: " + id));
    }

    public Role findRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not exist with name: " + roleName));
    }

    public ResponseEntity<Role> updateRole(long id, Role updatedRole) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not exist with id: " + id));
        RoleNameValidation(updatedRole);
        role.setRoleName(updatedRole.getRoleName());
        roleRepository.save(role);
        return ResponseEntity.ok(role);
    }

    public ResponseEntity<Map<String,Boolean>> deleteRole(long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not exist with id: " + id));
        roleRepository.delete(role);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
