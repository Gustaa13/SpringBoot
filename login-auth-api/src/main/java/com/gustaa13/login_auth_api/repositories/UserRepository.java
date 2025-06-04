package com.gustaa13.login_auth_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustaa13.login_auth_api.domain.user.User;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String login);

}
