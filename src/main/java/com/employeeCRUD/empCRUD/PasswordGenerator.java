package com.employeeCRUD.empCRUD;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("fkr123 -> " + encoder.encode("fkr123"));
        System.out.println("abebe123 -> " + encoder.encode("abebe123"));
        System.out.println("alex123 -> " + encoder.encode("alex123"));
    }
}