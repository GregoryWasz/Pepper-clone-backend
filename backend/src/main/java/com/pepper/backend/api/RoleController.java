package com.pepper.backend.api;

import com.pepper.backend.model.Role;
import com.pepper.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public Role addRole(@Valid @RequestBody Role role) {
        return roleService.addRole(role);
    }

    @GetMapping("/roles/{id}")
    public Role getRoleById(@PathVariable int id) {
        return roleService.findRoleById(id);
    }

    @GetMapping("/roles/{roleName}")
    public ResponseEntity<Role> getRoleByRoleName(@PathVariable String roleName) {
        Role role = roleService.findRoleByName(roleName);
        return ResponseEntity.ok(role);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable long id, @Valid @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteRole(@PathVariable long id) {
        return roleService.deleteRole(id);
    }

    // TODO VALIDATION REGEX AND STRINGBUILDER
    // TODO SECURITY ONLY ADMIN ACCESS
}

