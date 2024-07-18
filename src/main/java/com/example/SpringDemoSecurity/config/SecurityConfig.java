package com.example.SpringDemoSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration //@Configuration: This annotation indicates that the class is a source of bean definitions. Spring will process this class to generate Spring Beans.
@EnableWebSecurity //@EnableWebSecurity: This annotation enables Spring Security's web security support and provides the Spring MVC integration.
// Enable method-level security
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //defines the security filter chain, configuring how HTTP requests are secured.
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admins/**").hasRole("ADMIN")
                        .requestMatchers("/users/**").hasAnyRole("USER","ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();

    }
/*
http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()):
This line configures the HttpSecurity object to require authentication for all requests.

anyRequest().authenticated() means that all requests must be authenticated.
 */

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
//        return authenticationConfiguration.getAuthenticationManager();
//    }
/*
authenticationConfiguration.getAuthenticationManager():
This method retrieves the AuthenticationManager from the AuthenticationConfiguration.
The AuthenticationManager is responsible for processing authentication requests.
 */

}
