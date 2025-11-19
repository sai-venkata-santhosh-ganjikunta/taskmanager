package com.example.taskmanager.controller;

import com.example.taskmanager.dto.AuthRequest;
import com.example.taskmanager.dto.AuthResponse;
import com.example.taskmanager.dto.RegisterRequest;
import com.example.taskmanager.entity.Role;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.RoleRepository;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager; // we'll use simple manual auth
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authManager, UserRepository userRepo, RoleRepository roleRepo, JwtUtil jwtUtil, BCryptPasswordEncoder encoder) {
        this.authenticationManager = authManager;
        this.userRepository = userRepo;
        this.roleRepository = roleRepo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        User u = new User();
        u.setEmail(req.email());
        u.setName(req.name());
        u.setPassword(passwordEncoder.encode(req.password()));
        Role userRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> {
            Role r = new Role();
            r.setName("ROLE_USER");
            return roleRepository.save(r);
        });
        u.setRoles(Set.of(userRole));
        userRepository.save(u);
        return ResponseEntity.ok("registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        // authenticate manually by loading user and checking password
        User u = userRepository.findByEmail(req.email()).orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(req.password(), u.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        String token = jwtUtil.generateToken(u.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, "Bearer"));
    }
}
