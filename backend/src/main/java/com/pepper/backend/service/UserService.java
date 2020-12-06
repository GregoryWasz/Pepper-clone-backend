package com.pepper.backend.service;

import com.pepper.backend.exception.ResourceNotFoundException;
import com.pepper.backend.model.Role;
import com.pepper.backend.model.User;
import com.pepper.backend.repository.RoleRepository;
import com.pepper.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " ));
        user.setRole(role);
        return userRepository.save(user);
    }

}
