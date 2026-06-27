package com.employeeCRUD.empCRUD.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Public endpoints
                        .requestMatchers("/api/auth/login").permitAll()

                        // Employee endpoints
                        .requestMatchers(HttpMethod.GET, "/api/employee/all").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employee/*").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/employee/*").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/employee/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/employee/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/employee/*").hasAnyRole("MANAGER", "ADMIN")

                        // Job endpoints
                        .requestMatchers(HttpMethod.GET, "/api/job/all").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/job/*").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/job/*").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/job/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/job/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/job/*").hasAnyRole("MANAGER", "ADMIN")

                        // Project endpoints
                        .requestMatchers(HttpMethod.GET, "/api/project/all").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/project/*").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/project/*").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/project/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/project/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/project/*").hasAnyRole("MANAGER", "ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}