
package com.example.authapi.auth;

import com.example.authapi.security.JwtService;
import com.example.authapi.user.User;
import com.example.authapi.user.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {

        String email = request.email()
                .trim()
                .toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Email already registered"
            );
        }

        String passwordHash =
                passwordEncoder.encode(request.password());

        User user =
                new User(email, passwordHash);

        userRepository.save(user);

        String token =
                jwtService.generateToken(
                        user.getId(),
                        user.getEmail()
                );

        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                token
        );
    }

    public AuthResponse login(LoginRequest request) {

        String email = request.email()
                .trim()
                .toLowerCase();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Invalid email or password"
                                )
                        );

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.password(),
                        user.getPasswordHash()
                );

        if (!passwordMatches) {
            throw new IllegalArgumentException(
                    "Invalid email or password"
            );
        }

        String token =
                jwtService.generateToken(
                        user.getId(),
                        user.getEmail()
                );

        return new AuthResponse(
                user.getId(),
                user.getEmail(),
                token
        );
    }
}