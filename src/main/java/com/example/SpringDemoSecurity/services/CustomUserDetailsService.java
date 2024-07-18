package com.example.SpringDemoSecurity.services;

import com.example.SpringDemoSecurity.entities.User;
import com.example.SpringDemoSecurity.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService { //used to retrieve user details.

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Not FOUND!"));

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(authority));
    }
}
/*
- String username: The username parameter is used to identify the user.
- User user: The user is fetched from the repository using userRepository.findByUsername(username).
 If the user is not found, a UsernameNotFoundException is thrown.
- GrantedAuthority authority: This represents an authority granted to the user, typically a role.
 Here, a SimpleGrantedAuthority is created using the user's role.
- UserDetails: An instance of org.springframework.security.core.userdetails.User is created and returned.
This user object includes the username, password, and granted authorities (roles).
 */

/*
Collections.singletonList(authority)

Collections.singletonList(authority) is used to create a list with a single granted authority,
ensuring immutability and providing a straightforward way to pass the user's authority to
the User constructor in Spring Security.
 */