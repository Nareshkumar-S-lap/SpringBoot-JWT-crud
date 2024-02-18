package com.example.springJwt.repository;

import com.example.springJwt.model.Role;
import com.example.springJwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    boolean findByRole(Role user);
    
}
