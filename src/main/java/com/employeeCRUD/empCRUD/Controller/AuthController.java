package com.employeeCRUD.empCRUD.Controller;

import com.employeeCRUD.empCRUD.Dto.AuthRequest;
import com.employeeCRUD.empCRUD.Dto.AuthResponse;
import com.employeeCRUD.empCRUD.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(new AuthResponse(jwt, userDetails.getUsername(), "Login successful"));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new AuthResponse(null, null, "Invalid credentials"));
        }
    }
}