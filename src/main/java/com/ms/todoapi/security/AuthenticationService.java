package com.ms.todoapi.security;

import com.ms.todoapi.dto.AuthRequest;
import com.ms.todoapi.dto.AuthResponse;
import com.ms.todoapi.model.entity.Role;
import com.ms.todoapi.model.entity.User;
import com.ms.todoapi.repository.RoleRepository;
import com.ms.todoapi.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse signUp(AuthRequest authRequest) {
        Role userRole = roleRepository.findByRole("ROLE_USER").orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setRoles(Set.of(userRole));

        userService.save(user);

        String jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }

    public AuthResponse signin(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        User user = userService.getByEmail(authRequest.getEmail());
        String jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }
}
