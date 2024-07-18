package com.example.SpringDemoSecurity.services;


import com.example.SpringDemoSecurity.entities.User;
import com.example.SpringDemoSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllAdmins() {
        // Filter users with role ROLE_ADMIN
        return userRepository.findAll().stream()
                .filter(user -> "ROLE_ADMIN".equals(user.getRole()))
                .collect(Collectors.toList());
    }
    //    public List<User> getAllAdmins() {
//        List<User> allUsers = userRepository.findAll();
//        List<User> adminUsers = new ArrayList<>();
//
//        for (User user : allUsers) {
//            if ("ADMIN".equals(user.getRole())) {
//                adminUsers.add(user);
//            }
//        }
//
//        return adminUsers;
//    }

    public User getAdmin(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        return (user != null && "ROLE_ADMIN".equals(user.getRole())) ? user : null;
    }

    public User createAdmin(User user) {
        user.setRole("ROLE_ADMIN");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
