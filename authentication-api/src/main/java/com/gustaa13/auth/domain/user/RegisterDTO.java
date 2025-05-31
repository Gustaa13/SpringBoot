package com.gustaa13.auth.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
