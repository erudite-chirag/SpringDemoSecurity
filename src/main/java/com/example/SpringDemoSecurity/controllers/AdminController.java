package com.example.SpringDemoSecurity.controllers;

import com.example.SpringDemoSecurity.entities.User;
import com.example.SpringDemoSecurity.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // All Admins

    @GetMapping("/")
    public List<User> getAllAdmins() {
        return this.adminService.getAllAdmins();
    }

    // Get single admin
    @GetMapping("/{username}")
    public User getAdmin(@PathVariable("username") String username) {
        return this.adminService.getAdmin(username);
    }

    // Create new admin

    @PostMapping("/")
    public User createAdmin(@RequestBody User user) {
        return this.adminService.createAdmin(user);
    }
}