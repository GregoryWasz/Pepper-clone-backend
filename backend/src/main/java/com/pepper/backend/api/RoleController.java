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

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/roles")
    public Role addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRole(@PathVariable int id){
        Role role = roleService.findRoleById(id);
        return ResponseEntity.ok(role);
    }

    // TODO GET by id (view Role)
    // TODO Get by name
    // TODO PUT change by id (change RoleName)
    // TODO DELETE by id (delete Role)
    // TODO VALIDATION REGEX AND STRINGBUILDER
    // TODO SECURITY ONLY ADMIN ACCESS
}

