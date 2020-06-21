package com.agrocheck.core.rest_modules.users.repository;

import com.agrocheck.core.rest_modules.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
}
