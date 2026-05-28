package com.kvl.library.controller;

import com.kvl.library.model.User;
import com.kvl.library.repository.UserRepository;
import com.kvl.library.security.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthApiController(AuthenticationManager authenticationManager,
                             UserDetailsService userDetailsService,
                             JwtUtils jwtUtils,
                             PasswordEncoder passwordEncoder,
                             UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> authRequest) {
        String username = authRequest.get("username");
        String password = authRequest.get("password");

        // Аутентификация пользователя средствами Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Генерация токена при успешном входе
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtils.generateToken(userDetails.getUsername());

        return Map.of("token", jwt);
    }

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> registerRequest) {
        String username = registerRequest.get("username");
        String password = registerRequest.get("password");

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Пользователь уже существует");
        }

        User newUser = new User();
        newUser.setUsername(username);
        // Хешируем пароль перед записью в БД
        newUser.setPassword(passwordEncoder.encode(password));
        // По умолчанию регистрируем как обычного пользователя
        newUser.setRole("ROLE_USER");

        userRepository.save(newUser);
        return "Пользователь успешно зарегистрирован!";
    }
}
