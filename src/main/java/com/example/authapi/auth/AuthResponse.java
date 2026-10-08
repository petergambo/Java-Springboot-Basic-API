
package com.example.authapi.auth;

public record AuthResponse(
        Long id,
        String email,
        String token
) {
}