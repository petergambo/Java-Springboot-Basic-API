
package com.example.authapi.user;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public UserResponse me(
            Authentication authentication
    ) {

        String email =
                authentication.getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow();

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

    public record UserResponse(
            Long id,
            String email,
            java.time.LocalDateTime createdAt
    ) {
    }
}