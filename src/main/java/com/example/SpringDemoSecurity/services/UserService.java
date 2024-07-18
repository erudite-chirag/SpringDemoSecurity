package com.example.SpringDemoSecurity.services;

import com.example.SpringDemoSecurity.entities.User;
import com.example.SpringDemoSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;

        User user1 = new User(1L, "abc",passwordEncoder.encode("abc"), "abc@gmail.com", "ROLE_USER");
        userRepository.save(user1);

        User user2 = new User(2L, "xyz", passwordEncoder.encode("xyz"), "xyz@gmail.com", "ROLE_USER");
        userRepository.save(user2);

    // Add default admin to the repository if not already present
        User user3 = new User(3L, "admin", passwordEncoder.encode("admin"), "admin@gmail.com", "ROLE_ADMIN");
        userRepository.save(user3);
    }

    // Create a new user and save it to the database
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a single user by username from the database
    public User getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }



}
