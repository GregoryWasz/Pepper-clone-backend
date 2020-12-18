package com.pepper.backend.api;

import com.pepper.backend.dto.MessageDto;
import com.pepper.backend.model.Role;
import com.pepper.backend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("")
    public Role addRole(@Valid @RequestBody Role role) {
        return roleService.addRole(role);
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable int id) {
        return roleService.findRoleById(id);
    }

    @GetMapping("/name/{roleName}")
    public ResponseEntity<Role> getRoleByRoleName(@PathVariable String roleName) {
        Role role = roleService.findRoleByName(roleName);
        return ResponseEntity.ok(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable long id, @Valid @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok(new MessageDto("Role successfully deleted"));
    }
}

