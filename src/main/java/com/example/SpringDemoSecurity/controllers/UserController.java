package com.example.SpringDemoSecurity.controllers;

import com.example.SpringDemoSecurity.entities.User;
import com.example.SpringDemoSecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public  User createUser(@RequestBody User user){
        return  this.userService.createUser(user);
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return this.userService.getUser(username);
    }
}
