package com.pepper.backend.api;

import com.pepper.backend.model.Role;
import com.pepper.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/role")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/role")
    public Role addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getRole(@PathVariable int id){
        Role role = roleService.findRoleById(id);
        return ResponseEntity.ok(role);
    }

    // TODO GET by id (view Role)
    // TODO PUT change by id (change RoleName)
    // TODO DELETE by id (delete Role)
    // TODO VALIDATION
}

