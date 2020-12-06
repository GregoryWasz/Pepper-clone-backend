package com.pepper.backend.service;

import com.pepper.backend.exception.ResourceNotFoundException;
import com.pepper.backend.model.Role;
import com.pepper.backend.model.User;
import com.pepper.backend.repository.RoleRepository;
import com.pepper.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityExistsException("User " + username + " doesn't exist"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " ));
        user.setRole(role);
        return userRepository.save(user);
    }

}
