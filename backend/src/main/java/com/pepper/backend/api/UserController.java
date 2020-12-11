package com.pepper.backend.api;


import com.pepper.backend.dto.UsernameAndEmailDto;
import com.pepper.backend.model.User;
import com.pepper.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UsernameAndEmailDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UsernameAndEmailDto getUserById(@PathVariable Long id) {
        return userService.getUsernameAndEmailById(id);
    }

    @PostMapping("/users/register")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable long id){
        return userService.deleteUser(id);
    }

    @PatchMapping("users/change/username/{id}")
    public ResponseEntity<String> changeUsername(@PathVariable long id, @RequestBody User user){
        return userService.changeUsername(id, user);
    }

    @PatchMapping("users/change/email/{id}")
    public ResponseEntity<String> changeEmail(@PathVariable long id, @RequestBody User user){
        return userService.changeEmail(id, user);
    }

    @PatchMapping("users/change/password/{id}")
    public ResponseEntity<String> changePassword(@PathVariable long id, @RequestBody User user){
        return userService.changePassword(id, user);
    }
}
