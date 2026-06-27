package com.employeeCRUD.empCRUD.Service;

import com.employeeCRUD.empCRUD.Entity.User;
import com.employeeCRUD.empCRUD.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DataInitializationService implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Create users only if they don't exist
        if (!userRepository.existsByUsername("fkre")) {
            Set<String> roles = new HashSet<>();
            roles.add("EMPLOYEE");
            User user1 = new User("fkre", passwordEncoder.encode("fkr123"), roles);
            userRepository.save(user1);
        }

        if (!userRepository.existsByUsername("abebe")) {
            Set<String> roles = new HashSet<>();
            roles.add("EMPLOYEE");
            roles.add("MANAGER");
            User user2 = new User("abebe", passwordEncoder.encode("abebe123"), roles);
            userRepository.save(user2);
        }

        if (!userRepository.existsByUsername("alex")) {
            Set<String> roles = new HashSet<>();
            roles.add("EMPLOYEE");
            roles.add("MANAGER");
            roles.add("ADMIN");
            User user3 = new User("alex", passwordEncoder.encode("alex123"), roles);
            userRepository.save(user3);
        }
    }
}